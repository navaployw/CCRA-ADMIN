/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arg.ccra.adminonline.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.arg.ccra.adminonline.models.CountDataBlock;
import com.arg.ccra.adminonline.models.DataBlock;
import com.arg.ccra.adminonline.models.Duplicate;
import com.arg.ccra.adminonline.models.User;
import com.arg.ccra.adminonline.models.UserMapper;

import jakarta.persistence.EntityManagerFactory;

public class AdminLoginDAO {

     
    private final JdbcTemplate jdbcTemplateApi;
    private final String BLOCK_NAME = "blockName";
    private final String BLOCK_URL = "blockUrl";
    private final String BLOCK_DESC = "blockDesc";
    private final String STATUS = "status";
    private final String BLOCK_ID = "blockId";
    private final String USER_LOGIN = "userLogin";
    private final Logger logger = (Logger) LoggerFactory.getLogger(AdminLoginDAO.class);
    private String infoLog = "";

    public AdminLoginDAO(JdbcTemplate jdbcTemplateApi) {
        this.jdbcTemplateApi = jdbcTemplateApi;
    }

    public User login(String userName) {
        logger.info("<<<AdminLoginDAO>>>");
        List<Object>params = new ArrayList<>();
        params.add(userName);
        String sql = "select * from spm_user u \n" +
                     "where (u.GROUPID = 1 or u.GROUPID in(select convert(numeric,CTRLVALUE) from API_CTRL where CTRLTYPE = 'GroupAdmModule' and CTRL_FLAG = 1))\n" +
                     "and u.ADMINFLAG = 1 \n" +
                     "and u.DELETED = 0 and u.USERID = ?";
        List<User> results = this.jdbcTemplateApi.query(sql,params.toArray(), BeanPropertyRowMapper.newInstance(User.class));
        // infoLog = String.format("login>>>>> %s", results);
        // logger.info(infoLog);
        logger.info(" FIND user size : "+ results.size());
        return results.size()>0?results.get(0):null;
    }

    public User findByUid(Long uid) {
        logger.info("User UID "+ uid);
        if(uid == null)
        {
            return null;
        } 

        String sql = "SELECT UID, USERID FROM spm_user WHERE uid=?";
        Object[] params = new Object[]{uid};
        User streetName = (User) jdbcTemplateApi.queryForObject(
                sql, params, new UserMapper());
    
        return streetName;
    }

    public void insertSessionLog(BigDecimal uid, String ipAddress, Boolean loginFlag) {
        logger.info("<<<insertSessionLog>>>");
        String sql ="";
        if(loginFlag){// login success
           sql = "INSERT INTO API_SPM_SESSION VALUES(?,getDate(),null,?,5,getDate(),1,?)";
        }
        else{// login fail
             sql = "INSERT INTO API_SPM_SESSION VALUES(?,getDate(),getDate(),?,5,getDate(),1,?)";
        }
        Object[] params = new Object[]{uid, ipAddress, loginFlag};
        this.jdbcTemplateApi.update(sql, params);
    }

    public void insertTranDetailSystemAccess(BigDecimal uid, BigDecimal groupId, BigDecimal groupAiId, String username, Long actionId) {
        logger.info("<<<insertTranDetailSystemAccess>>>");
        String sql = "INSERT INTO API_TRANS_DETAIL_SYSTEM_ACCESS VALUES (getDate(),?,?,?,1,?,?,?)";
        Object[] params = new Object[]{groupId, uid, actionId, uid, groupAiId, username};
        this.jdbcTemplateApi.update(sql, params);
    }

    public void updateSessionLog(BigDecimal uid) {
        logger.info("<<<logout>>>");
        String sql = "UPDATE API_SPM_SESSION SET LOGOUTDATE = ? WHERE UID = ? AND LOGOUTDATE IS NULL";
        Object[] params = new Object[]{new Date(), uid};
        this.jdbcTemplateApi.update(sql, params);
    }

    public List<DataBlock> getDataBlockList(String txt) {
        infoLog = String.format("<<<getDataBlockList>>> %s", txt);
        logger.info(infoLog);
        String sql = "";
        List<DataBlock> results;
        if (txt == null || txt.equals("")) {
            sql = "select BLOCKID,BLOCKNAME,BLOCKDESC,BLOCKURL from API_DATABLOCK where DELETED = 0 order by BLOCKNAME ";
            infoLog = String.format("sql>>>>> %s", sql);
            logger.info(infoLog);
            results = this.jdbcTemplateApi.query(sql, BeanPropertyRowMapper.newInstance(DataBlock.class));
        } else {
            sql = "select BLOCKID,BLOCKNAME,BLOCKDESC,BLOCKURL from API_DATABLOCK where BLOCKNAME like ? AND DELETED = 0 order by BLOCKNAME ";
            String txtLike = "%" + txt + "%";
            Object[] params = new Object[]{txtLike};
            infoLog = String.format("sql>>>>> %s", sql);
            logger.info(infoLog);
            results = this.jdbcTemplateApi.query(sql, BeanPropertyRowMapper.newInstance(DataBlock.class), params);
        }
        infoLog = String.format("getDataBlockList>>>>> %s", results);
        logger.info(infoLog);
        return results;
    }

    public Duplicate insertUpdateDataBlockList(String reqest) {
        infoLog = String.format("<<<insertUpdateDataBlockListDao>>> %s", reqest);
        logger.info(infoLog);
        JSONObject json = new JSONObject(reqest);
        String blockName = json.get(BLOCK_NAME).toString();
        String blockUrl = ((json.has(BLOCK_URL) && !json.isNull(BLOCK_URL))) ? json.getString(BLOCK_URL) : null;
        String blockDesc = ((json.has(BLOCK_DESC) && !json.isNull(BLOCK_DESC))) ? json.getString(BLOCK_DESC) : null;
        String status = ((json.has(STATUS) && !json.isNull(STATUS))) ? json.getString(STATUS) : null;
        Long blockId = ((json.has(BLOCK_ID) && !json.isNull(BLOCK_ID))) ? json.getLong(BLOCK_ID) : null;
        String userLogin = ((json.has(USER_LOGIN) && !json.isNull(USER_LOGIN))) ? json.getString(USER_LOGIN) : null;
        infoLog = String.format("<<<blockName>>> %s", blockName);
        logger.info(infoLog);
        infoLog = String.format("<<<blockUrl>>> %s", blockUrl);
        logger.info(infoLog);
        infoLog = String.format("<<<blockDesc>>> %s", blockDesc);
        logger.info(infoLog);
        infoLog = String.format("<<<status>>> %s", status);
        logger.info(infoLog);
        infoLog = String.format("<<<blockId>>> %s", blockId);
        logger.info(infoLog);
        infoLog = String.format("<<<userLogin>>> %s", userLogin);
        logger.info(infoLog);
        if (status != null && status.equals("new")) {
            String sql = "declare @Result tinyint "
                    .concat("declare @ResultMessage varchar(256) ")
                    .concat("declare @BLOCKID int ")
                    .concat("declare @BLOCKNAME VARCHAR(255) ")
                    .concat("declare @BLOCKURL VARCHAR(255) ")
                    .concat("exec [check_datablock_duplicate] @result , @ResultMessage ,@BLOCKID , ?, ? ");
            Object[] params = new Object[]{blockName, blockUrl};
            List<Duplicate> results = this.jdbcTemplateApi.query(sql, BeanPropertyRowMapper.newInstance(Duplicate.class), params);
            infoLog = String.format("insertSessionLog>>>>> %s", results);
            logger.info(infoLog);
            if (results.get(0).getDuplicate()) {
                return results.get(0);
            } else {

                String sqlInsert = "INSERT INTO API_DATABLOCK (BLOCKNAME,BLOCKDESC,BLOCKURL,CREATEBY,CREATEDATE,UPDATEBY,UPDATEDATE,DELETEBY,DELETEDATE,DELETED) VALUES (?,?,?,?,?,null,null,null,null,0);";
                Object[] paramsInsert = new Object[]{blockName, blockDesc, blockUrl, userLogin, new Date()};
                this.jdbcTemplateApi.update(sqlInsert, paramsInsert);

                Duplicate du = new Duplicate();
                du.setDuplicate(Boolean.FALSE);
                du.setChkRemark("Insert Alternative Data Success");
                return du;
            }
        }
        else {
            String sql = "declare @Result tinyint "
                    .concat("declare @ResultMessage varchar(256) ")
                    .concat("declare @BLOCKID int ")
                    .concat("declare @BLOCKNAME VARCHAR(255) ")
                    .concat("declare @BLOCKURL VARCHAR(255) ")
                    .concat("exec [check_datablock_duplicate] @result , @ResultMessage ,?, ?, ? ");
            Object[] params = new Object[]{blockId, blockName, blockUrl};
            List<Duplicate> results = this.jdbcTemplateApi.query(sql, BeanPropertyRowMapper.newInstance(Duplicate.class), params);
            infoLog = String.format("insertSessionLog>>>>> %s", results);
            logger.info(infoLog);

            if (results.get(0).getDuplicate()) {
                return results.get(0);
            } else {
                String sqlInsert = "UPDATE API_DATABLOCK SET BLOCKNAME=?,BLOCKURL=?,BLOCKDESC=?,UPDATEDATE = ?,UPDATEBY=? WHERE BLOCKID = ?";
                Object[] paramsInsert = new Object[]{blockName, blockUrl, blockDesc, new Date(), userLogin, blockId};
                this.jdbcTemplateApi.update(sqlInsert, paramsInsert);

                Duplicate du = new Duplicate();
                du.setDuplicate(Boolean.FALSE);
                du.setChkRemark("Update Alternative Data Success");
                return du;
            }
        }

    }

    public Duplicate removeDataBlockList(String reqest) {
        infoLog = String.format("<<<removeDataBlockList>>> %s", reqest);
        logger.info(infoLog);
        JSONObject json = new JSONObject(reqest);
        String blockName = json.get(BLOCK_NAME).toString();
        String blockUrl = ((json.has(BLOCK_URL) && !json.isNull(BLOCK_URL))) ? json.getString(BLOCK_URL) : null;
        String blockDesc = ((json.has(BLOCK_DESC) && !json.isNull(BLOCK_DESC))) ? json.getString(BLOCK_DESC) : null;
        String status = ((json.has(STATUS) && !json.isNull(STATUS))) ? json.getString(STATUS) : null;
        Long blockId = ((json.has(BLOCK_ID) && !json.isNull(BLOCK_ID))) ? json.getLong(BLOCK_ID) : null;
        String userLogin = ((json.has(USER_LOGIN) && !json.isNull(USER_LOGIN))) ? json.getString(USER_LOGIN) : null;
        infoLog = String.format("<<<blockName>>> %s", blockName);
        logger.info(infoLog);
        infoLog = String.format("<<<blockUrl>>> %s", blockUrl);
        logger.info(infoLog);
        infoLog = String.format("<<<blockDesc>>> %s", blockDesc);
        logger.info(infoLog);
        infoLog = String.format("<<<status>>> %s", status);
        logger.info(infoLog);
        infoLog = String.format("<<<blockId>>> %s", blockId);
        logger.info(infoLog);
        infoLog = String.format("<<<userLogin>>> %s", userLogin);
        logger.info(infoLog);
        String sqlCheckUse = "declare @Result tinyint "
                .concat("declare @ResultMessage varchar(256) ")
                .concat("declare @BLOCKID int ")
                .concat("exec [count_using_datablock] @result , @ResultMessage ,?");
        Object[] paramsCheckUse = new Object[]{blockId};
        List<Duplicate> results = this.jdbcTemplateApi.query(sqlCheckUse, BeanPropertyRowMapper.newInstance(Duplicate.class), paramsCheckUse);
        infoLog = String.format("insertSessionLog>>>>> %s", results);
        logger.info(infoLog);

        String sqlUpdate = "UPDATE API_DATABLOCK SET DELETEBY=?,DELETEDATE=?,DELETED=1 WHERE BLOCKID = ?";
        Object[] paramsUpdate = new Object[]{userLogin, new Date(), blockId};
        this.jdbcTemplateApi.update(sqlUpdate, paramsUpdate);

        String sql = "DELETE "
                .concat("FROM API_MAP_USER_DATABLOCK ")
                .concat("WHERE BLOCKID = ? ");
        Object[] params = new Object[]{blockId};
        this.jdbcTemplateApi.update(sql, params);

        Duplicate du = new Duplicate();
        du.setDuplicate(Boolean.FALSE);
        du.setChkRemark("Delete Alternative Data Success");
        return du;

    }

    public List<CountDataBlock> beforeRemoveDataBlockList(String reqest) {
        infoLog = String.format("<<<removeDataBlockList>>> %s", reqest);
        logger.info(infoLog);
        JSONObject json = new JSONObject(reqest);
        String blockName = json.get(BLOCK_NAME).toString();
        String blockUrl = ((json.has(BLOCK_URL) && !json.isNull(BLOCK_URL))) ? json.getString(BLOCK_URL) : null;
        String blockDesc = ((json.has(BLOCK_DESC) && !json.isNull(BLOCK_DESC))) ? json.getString(BLOCK_DESC) : null;
        String status = ((json.has(STATUS) && !json.isNull(STATUS))) ? json.getString(STATUS) : null;
        Long blockId = ((json.has(BLOCK_ID) && !json.isNull(BLOCK_ID))) ? json.getLong(BLOCK_ID) : null;
        String userLogin = ((json.has(USER_LOGIN) && !json.isNull(USER_LOGIN))) ? json.getString(USER_LOGIN) : null;

        infoLog = String.format("<<<blockName>>> %s", blockName);
        logger.info(infoLog);
        infoLog = String.format("<<<blockUrl>>> %s", blockUrl);
        logger.info(infoLog);
        infoLog = String.format("<<<blockDesc>>> %s", blockDesc);
        logger.info(infoLog);
        infoLog = String.format("<<<status>>> %s", status);
        logger.info(infoLog);
        infoLog = String.format("<<<blockId>>> %s", blockId);
        logger.info(infoLog);
        infoLog = String.format("<<<userLogin>>> %s", userLogin);
        logger.info(infoLog);
        String sqlCheckUse = "declare @Result tinyint "
                .concat("declare @ResultMessage varchar(256) ")
                .concat("declare @DT_id int = ? ")
                .concat("exec [count_using_datablock] @result , @ResultMessage ,@DT_id");
        Object[] paramsCheckUse = new Object[]{blockId};
        List<CountDataBlock> results = this.jdbcTemplateApi.query(sqlCheckUse, BeanPropertyRowMapper.newInstance(CountDataBlock.class), paramsCheckUse);
        infoLog = String.format("insertSessionLog>>>>> %s", results);
        logger.info(infoLog);
        return results;

    }
}
