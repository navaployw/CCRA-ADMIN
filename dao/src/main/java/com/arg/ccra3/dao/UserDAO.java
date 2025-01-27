
package com.arg.ccra3.dao;

import ch.qos.logback.classic.Logger;
import static com.arg.cb2.inquiry.PreferLanguage.*;
import com.arg.ccra3.dao.repo.UserAPIRepo;
import com.arg.ccra3.model.User;
import com.arg.ccra3.model.api.UserAPI;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;


public class UserDAO {
    
    private UserAPIRepo userApiRepo;
    private JdbcTemplate jdbcTemplateAPI;
    
    private final Logger logger = (Logger) LoggerFactory.getLogger(UserDAO.class);
    
    public UserDAO(JdbcTemplate jdbcTemplateAPI, UserAPIRepo userApiRepo){
        this.jdbcTemplateAPI = jdbcTemplateAPI;
        this.userApiRepo = userApiRepo;
    }
    public void setUserAPIRepo(UserAPIRepo userApiRepo){
        this.userApiRepo = userApiRepo;
    }
    
    public User getUserFromUserID(String userID){
        User user = new User();
        user.setUserID(userID);
        
        final String SQL = "select * from spm_user u " +
            "inner join SPM_GROUP g on g.GROUPID = u.GROUPID " +
            "left join base_country bc on bc.id = g.COUNTRY " +
            "where u.USERID = ?";
        
        this.jdbcTemplateAPI.query(
            SQL,
            (ResultSet rst) -> {
                do{
                    user.setUID(rst.getInt("UID"));
                    user.setAddressEng1(rst.getString("ADDRESS_1"));
                    user.setAddressEng2(rst.getString("ADDRESS_2"));
                    user.setAddressEng3(rst.getString("ADDRESS_3"));
                    user.setCountry(rst.getString("DESCRIPTION_EN"));
                    user.setProvinceEnglish(rst.getString("PROVINCE"));
                    user.setCityEnglish(rst.getString("CITY"));
                    user.setAreaCode(rst.getString("POST_CODE"));
                    user.setPreferLanguage(rst.getString("PREFERREDLANGUAGE").equals("L") ? CHINESE : ENGLISH);
                    if(!rst.next())break;
                }while (rst.next());
            },
            new Object[]{userID}
        );
        
        return user;
    }
    
    public List<Object> getAPIUserList(){        
        List<Object> users = new LinkedList<>();
         final String SQL = "select u.UID,g.AICODE, u.USERID, u.AID from API_USER u left join SPM_GROUP g on u.GROUPID = g.GROUPID and g.DELETED = 0  and g.AIFLAG = 1  and g.AICODE is not null  where u.DELETED = 0  order by g.AICODE";
        
        this.jdbcTemplateAPI.query(
            SQL,
            (ResultSet rst) -> {
                do{
                    Map<String,Object> user = new HashMap<>();
                    user.put("uID", rst.getLong("UID"));
                    user.put("aID", rst.getLong("AID"));
                    user.put("aiCode", rst.getString("AICODE"));
                    user.put("userID", rst.getString("USERID"));
                    users.add(user);
                }while (rst.next());
            }
        );
        return users;
    }
    public Object getUserDetailByAID(long aID){
        Map<String,Object> user = new HashMap<>();
        final String SQL = "select a.AID, a.UID, a.GROUPAIID, a.GROUPID, a.USERID," +
            "a.THRESHOLD_WEEK,a.SECRETKEY,a.SECRET_START,a.SECRET_END, a.DISABLED" +
            ",g.GROUP_NAME_EN as groupInfo,g.AICODE as AICODE " +
            "from API_USER a with (nolock) " +
            "inner join SPM_GROUP g  with (nolock) on a.GROUPID = g.GROUPID " +
            "where a.deleted = 0 and a.AID = ?";
        Object[] params = new Object[]{aID};
        this.jdbcTemplateAPI.query(
            SQL,
            (ResultSet rst) -> {
                do{
                    user.put("aID", rst.getLong("AID"));
                    user.put("uID", rst.getLong("UID"));
                    user.put("gaiid", rst.getLong("GROUPAIID"));
                    user.put("gid", rst.getLong("GROUPID")); 
                    user.put("userID", rst.getString("USERID"));
                    user.put("thresholdWeek", rst.getInt("THRESHOLD_WEEK"));
                    user.put("secretKey", rst.getString("SECRETKEY"));
                    user.put("secretStart", rst.getDate("SECRET_START"));
                    user.put("secretEnd", rst.getDate("SECRET_END"));
                    user.put("disabled", rst.getBoolean("DISABLED"));
                    user.put("groupInfo", rst.getString("groupInfo"));
                    user.put("aiCode", rst.getString("aiCode"));
                }while (rst.next());
            },params
        );
        return user;
    }
    public List<Object> getListDataBlock(){        
        List<Object> dataBlocks = new LinkedList<>();
        final String SQL = "select  BLOCKID,BLOCKNAME from API_DATABLOCK where DELETED = 0 order by BLOCKNAME";
        
        this.jdbcTemplateAPI.query(
            SQL,
            (ResultSet rst) -> {
                do{
                    Map<String,Object> dataBlock = new HashMap<>();
                    dataBlock.put("blockID", rst.getLong("BLOCKID"));
                    dataBlock.put("blockName", rst.getString("BLOCKNAME"));
                    dataBlocks.add(dataBlock);
                }while (rst.next());
            }
        );
        return dataBlocks;
    }
    public List<Long> getListDataBlockByUID(Long uID){   
        List<Long> dataBlocks = new LinkedList<>();
        final String SQL = "select BLOCKID,UID from API_MAP_USER_DATABLOCK where UID = ?";
        
        this.jdbcTemplateAPI.query(
            SQL,
            (ResultSet rst) -> {
                do{
                    dataBlocks.add(rst.getLong("BLOCKID"));
                }while (rst.next());
            },
            new Object[]{uID}
        );
        return dataBlocks;
    }
    
    
    
    public void deleteUserByAIDandUID(String by, long aID, long uID){
        String infoLog = String.format("AID:: %s",aID);
        logger.info(infoLog);
        infoLog = String.format("UID:: %s",uID);
        logger.info(infoLog);
        this.jdbcTemplateAPI.update(
            "update API_USER set DELETED = 1 , DELETEDBY = ?, DELETEDDATE = getdate() where AID = ? and UID = ? and DELETED = 0",
            new Object[]{by, aID, uID}
        );
        this.jdbcTemplateAPI.update(
            "delete from API_MAP_USER_DATABLOCK where UID = ?",
            new Object[]{uID}
        );
    }
    
    
//    public List<Map<String, Object>> searchWithUserId(String userId){
//        StringBuilder sql = new StringBuilder("declare @Keyword varchar(256) = ");
//        
//        if(userId == null || userId.trim().isEmpty())
//            sql.append("null");
//        else
//            sql.append("'").append(userId).append("'");
//        
//        sql.append(" declare @Result tinyint declare @ResultMessage varchar(256) exec [get_list_user_prod] @result , @ResultMessage ,@Keyword ");
//        String sqlStr = sql.toString();
//        logger.info(sqlStr);
//        return jdbcTemplateAPI.queryForList(sql.toString());
//    }
     public List<Map<String, Object>> searchWithUserId(String userId) {
        StringBuilder sql = new StringBuilder("declare @Keyword varchar(256) = ?");
        List<Object> params = new ArrayList<>();
        params.add(userId); 
        
        sql.append(" declare @Result tinyint declare @ResultMessage varchar(256) exec [get_list_user_prod] @result , @ResultMessage ,@Keyword ");
        
        String sqlStr = sql.toString();
        logger.info(sqlStr);
        
        return jdbcTemplateAPI.queryForList(sqlStr, params.toArray());
    }
    public Map<String, Object> checkUserDuplicateAI(long uid, long gid, long gaiid){
        String sql = "declare @Result tinyint declare @ResultMessage varchar(256) "
                + "declare @UID numeric(18,0) = ? "
                + "declare @GROUPID numeric(18,0)  = ? "
                + "declare @GROUPAIID numeric(18,0) = ? "
                + "exec [check_user_duplicate_ai] @result , @ResultMessage ,@UID , @GROUPID, @GROUPAIID";
        
        return this.jdbcTemplateAPI.queryForMap(
            sql,
            new Object[]{uid, gid, gaiid}
        );
    }
    
    
    public long saveUserInfo(JSONObject userJson) throws ParseException{
        String infoLog = String.format("saveUserInfo: %s",userJson.toString());
        logger.info(infoLog);
        
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        try{
            userJson.put("secretStart", dt.parse(userJson.optString("secretStart")));
        }catch(Exception ignore){
          logger.info(ignore.toString());
        }
        try{
            userJson.put("secretEnd", dt.parse(userJson.optString("secretEnd")));
        }catch(Exception ignore){
         logger.info(ignore.toString());
        }
        
        long uid = userJson.optLong("uid");
        insertDataBlockFromUID(uid, userJson.optJSONArray("newDataBlockIDs"));
        deleteDataBlockFromUID(uid, userJson.optJSONArray("delDataBlockIDs"));
        return saveUser(uid, userJson);
    }
    public long saveUser(long uid, JSONObject userJson){
        UserAPI user = new UserAPI();
        
        if(userJson.has("aid")){    
            Optional<UserAPI> userOptional = userApiRepo.findById(userJson.optLong("aid"));
            if(userOptional.isPresent()){
                user = userOptional.get();
                user.setUpdateby(userJson.optLong("by"));
                user.setUpdatedate(new Date());
            }

        }
        else{
            user = new UserAPI();
            user.setUserid(userJson.optString("userID"));
            user.setUid(uid);
            user.setGroupid(userJson.optLong("groupid"));
            user.setGroupaiid(userJson.optLong("groupaiid"));
            user.setCreatedby(userJson.optLong("by"));
            user.setDeleted(false);
        }
        
        user.setThreshold_week(userJson.optInt("thresholdWeek"));
        user.setSecretkey(userJson.optString("secretKey"));
        user.setSecret_start((Date)userJson.opt("secretStart"));
        logger.info("(Date)userJson.opt(secretEnd):: "+(Date)userJson.opt("secretEnd"));
        user.setSecret_end((Date)userJson.opt("secretEnd"));
        user.setDisabled(userJson.optBoolean("disabled"));
        
        return userApiRepo.save(user).getAid();
    }
//    public void insertDataBlockFromUID(long uid, JSONArray newDataBlockIDs){
//        if(newDataBlockIDs != null && newDataBlockIDs.isEmpty())return;
//        Date now = new Date();
//        for(var bid : newDataBlockIDs){
//            this.jdbcTemplateAPI.update(
//                "insert into API_MAP_USER_DATABLOCK(BLOCKID, UID, CREATEDATE) values(?,?,?)",
//                new Object[]{(int)bid, uid, now}
//            );
//        }
//    }
      public void insertDataBlockFromUID(long uid, JSONArray newDataBlockIDs) {
        if (newDataBlockIDs == null || newDataBlockIDs.isEmpty()) return;
        
        Date now = new Date();
        
        String sql = "INSERT INTO API_MAP_USER_DATABLOCK (BLOCKID, UID, CREATEDATE) VALUES (?, ?, ?)";
        
        for (Object bid : newDataBlockIDs) {
            int blockId = (int) bid;
            jdbcTemplateAPI.update(sql, blockId, uid, now);
        }
    }
//    public void DeleteDataBlockFromUID(long uid, JSONArray delDataBlockIDs){
//        if(delDataBlockIDs != null && delDataBlockIDs.isEmpty())return;
//        StringBuilder sql = new StringBuilder("delete from API_MAP_USER_DATABLOCK where UID = ");
//        sql.append(uid);
//        sql.append(" and BLOCKID in (");
//        String prefix = "";
//        for(var id : delDataBlockIDs){
//            sql.append(prefix);
//            sql.append(id);
//            prefix = ",";
//        }
//        sql.append(")");
//        
//        this.jdbcTemplateAPI.update(sql.toString());
//    }
    public void deleteDataBlockFromUID(long uid, JSONArray delDataBlockIDs) {
        if (delDataBlockIDs == null || delDataBlockIDs.isEmpty()) {
            return;
        }
    
        String sql = "DELETE FROM API_MAP_USER_DATABLOCK WHERE UID = ? AND BLOCKID IN (";
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < delDataBlockIDs.length(); i++) {
            if (i > 0) {
                placeholders.append(",");
            }
            placeholders.append("?");
        }
        sql += placeholders.toString() + ")";
    
        List<Object> params = new ArrayList<>();
        params.add(uid);
    
        for (Object id : delDataBlockIDs) {
            params.add((int) id);
        }
    
        this.jdbcTemplateAPI.update(sql, params.toArray());
    }
    public boolean isTokenActiveByAID(long aid){
        return jdbcTemplateAPI.queryForList(
            "select AID from API_TOKEN where EXPIRE_TIME > getdate() and AID = ?",
            aid
        ).size() > 0;
    }
}
