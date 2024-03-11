/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arg.ccra.adminonline.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.arg.ccra.adminonline.dao.AdminLoginDAO;
import com.arg.ccra.adminonline.dao.SpmGroupDao;
import com.arg.ccra.adminonline.models.CountDataBlock;
import com.arg.ccra.adminonline.models.DataBlock;
import com.arg.ccra.adminonline.models.Duplicate;
import com.arg.ccra.adminonline.models.LoginData;
import com.arg.ccra.adminonline.models.Member;
import com.arg.ccra.adminonline.models.ResponseModel;
import com.arg.ccra.adminonline.models.User;
import com.arg.ccra.adminonline.models.security.ViewApiUser;
import com.arg.ccra.adminonline.utils.SymmetricCipher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AdminService {

    @Autowired
    private JdbcTemplate jdbcTemplateAPI;

    @Autowired
    private JWTService jwtService;

    private final Logger logger = (Logger) LoggerFactory.getLogger(AdminService.class);
    private String logText ="";
    private final String USER_NAME ="username";
    private final String NOPERMISSION ="No Permission";
    private final String USERID ="userId";

    public User findByUid(Long uid){
        AdminLoginDAO adminLoginDAO = new AdminLoginDAO(jdbcTemplateAPI);
        return adminLoginDAO.findByUid(uid);
    }
    
    public LoginData checkLoginService(String requestJson , HttpServletResponse res, HttpServletRequest req) throws JSONException {
        logText = String.format("<<<<<checkLogin>>>> %s", requestJson);
        logger.info(logText);
        JSONObject json = new JSONObject(requestJson);
        logText = String.format("usernameIN>>>> %s", json.get(USER_NAME));
        logger.info(logText);
        logText = String.format("pwdIN>>>> %s", json.get("pwd"));
        logger.info(logText);
        HttpServletRequest requestHttp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logText = String.format("IP:: %s", requestHttp.getRemoteAddr());
        logger.info(logText);
        AdminLoginDAO adminLoginDAO = new AdminLoginDAO(jdbcTemplateAPI);
        LoginData loginResult = new LoginData();
        loginResult.setResult(Boolean.FALSE);
        loginResult.setMessage(NOPERMISSION);
        User user = adminLoginDAO.login(json.get(USER_NAME).toString());
            if (json.get(USER_NAME).equals(user.getUserName())) {
                try {
                    SymmetricCipher cy = SymmetricCipher.getInstance();
                    logText = String.format("usernameDB>>>> %s", user.getUserName());
                    logger.info(logText);
                    logText = String.format("pwdDB>>>> %s", cy.decrypt(user.getPassword()));
                    logger.info(logText);
                    //passw x
                    if ((json.get(USER_NAME).equals(user.getUserName())) && !(json.get("pwd").equals(cy.decrypt(user.getPassword())))) {
                        adminLoginDAO.insertSessionLog(new BigDecimal(user.getUID()), requestHttp.getRemoteAddr(), Boolean.FALSE);
                        adminLoginDAO.insertTranDetailSystemAccess(new BigDecimal(user.getUID()), new BigDecimal(user.getGroupID()), new BigDecimal(user.getGroupID()), user.getUserName(), 4L);
                        loginResult.setResult(Boolean.FALSE);
                        loginResult.setMessage("Invalid Password");
                    } //user x passw x
                    else if ((!json.get(USER_NAME).equals(user.getUserName())) && !(json.get("pwd").equals(cy.decrypt(user.getPassword())))) {
                        adminLoginDAO.insertSessionLog(new BigDecimal(user.getUID()), requestHttp.getRemoteAddr(), Boolean.FALSE);
                        adminLoginDAO.insertTranDetailSystemAccess(new BigDecimal(user.getUID()), new BigDecimal(user.getGroupID()), new BigDecimal(user.getGroupID()), user.getUserName(), 4L);
                        loginResult.setResult(Boolean.FALSE);
                        loginResult.setMessage(NOPERMISSION);
                        logger.info("USER X PASS X");
                    } //user passw true
                    else if ((json.get(USER_NAME).equals(user.getUserName())) && (json.get("pwd").equals(cy.decrypt(user.getPassword())))) {
                        
                        //expire
                        if (user.getExpireDate() != null && user.getExpireDate().before(new Date())) {
                            adminLoginDAO.insertSessionLog(new BigDecimal(user.getUID()), requestHttp.getRemoteAddr(), Boolean.FALSE);
                            adminLoginDAO.insertTranDetailSystemAccess(new BigDecimal(user.getUID()), new BigDecimal(user.getGroupID()), new BigDecimal(user.getGroupID()), user.getUserName(), 7L);
                            loginResult.setResult(Boolean.FALSE);
                            loginResult.setMessage("Your password has been expired");
                        } //Disabled
                        else if (user.getDisabled()) {
                            adminLoginDAO.insertSessionLog(new BigDecimal(user.getUID()), requestHttp.getRemoteAddr(), Boolean.FALSE);
                            adminLoginDAO.insertTranDetailSystemAccess(new BigDecimal(user.getUID()), new BigDecimal(user.getGroupID()), new BigDecimal(user.getGroupID()), user.getUserName(), 5L);
                            loginResult.setResult(Boolean.FALSE);
                            loginResult.setMessage("User has been disabled");
                        } //success
                        else {
                            loginResult.setResult(Boolean.TRUE);
                            loginResult.setMessage("Sucessful");
                            loginResult.setUserId(user.getUID());
                            loginResult.setGroupId(user.getGroupID());
                            loginResult.setGroupAIID(user.getGroupAIID());
                            adminLoginDAO.insertSessionLog(new BigDecimal(user.getUID()), requestHttp.getRemoteAddr(), Boolean.TRUE);
                            adminLoginDAO.insertTranDetailSystemAccess(new BigDecimal(user.getUID()), new BigDecimal(user.getGroupID()), new BigDecimal(user.getGroupID()), user.getUserName(), 1L);
                            String token = jwtService.generateToken(user.getUID().longValue(), user.getUserID());
                            res.setHeader("Authorization", "Bearer " + token);
                            
                        }
                        
                    }
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }

        logText = String.format("loginResult>>>> %s", loginResult);
        logger.info(logText);
        return loginResult;
    }

    public ResponseModel logoutService(String requestJson) {
        ResponseModel res = new ResponseModel();
        try {
            logText = String.format("<<<<<logout>>>> %s", requestJson);
            logger.info(logText);
            JSONObject json = new JSONObject(requestJson);
            logText = String.format("userId>>>> %s", json.get(USERID).toString());
            logger.info(logText);
            logText = String.format("groupId>>>> %s", json.get("groupId").toString());
            logger.info(logText);
            logText = String.format("username>>>> %s", json.get(USER_NAME).toString());
            logger.info(logText);
            BigDecimal userId = new BigDecimal(json.get(USERID).toString());
            BigDecimal groupId = new BigDecimal(json.get("groupId").toString());
            String username = json.get(USER_NAME).toString();
            AdminLoginDAO admin = new AdminLoginDAO(jdbcTemplateAPI);
            admin.updateSessionLog(new BigDecimal(json.get(USERID).toString()));
            admin.insertTranDetailSystemAccess(userId, groupId, groupId, username, 2L);
            res.setCode("200");
            res.setMessage("logout success");
        } catch (JSONException e) {
            logger.error(e.getMessage());
            res.setCode("404");
            res.setMessage(e.getMessage());
        }
        return res;
    }
    
    public List<DataBlock> getDataBlockList(String requestJson) throws JSONException{
        logText = String.format("<<<<<getDataBlockList>>>> %s", requestJson);
        logger.info(logText);
        JSONObject json = new JSONObject(requestJson);
        String searchTxt = json.get("searchTxt").toString();
        AdminLoginDAO admin = new AdminLoginDAO(jdbcTemplateAPI);
        List<DataBlock> results = admin.getDataBlockList(searchTxt);
        logText = String.format("<<<<<getDataBlockList results >>>> %s", results);
        logger.info(logText);
        return results;
    }
    
    public Duplicate insertUpdateDataBlockList(String requestJson) throws JSONException {
        logText = String.format("<<<<<insertUpdateDataBlockList>>>> %s", requestJson);
        logger.info(logText);
        AdminLoginDAO admin = new AdminLoginDAO(jdbcTemplateAPI);
        Duplicate result = admin.insertUpdateDataBlockList(requestJson);
        logText = String.format("<<<<<insertUpdateDataBlockList results >>>> %s", result);
        logger.info(logText);
        return result;
    }
    
    public List<CountDataBlock> beforeRemoveDataBlockList(@RequestBody String requestJson) throws JSONException {
        logText = String.format("<<<<<beforeRemoveDataBlockList>>>> %s", requestJson);
        logger.info(logText);
        AdminLoginDAO admin = new AdminLoginDAO(jdbcTemplateAPI);
        List<CountDataBlock> results = admin.beforeRemoveDataBlockList(requestJson);
        logText = String.format("<<<<<beforeRemoveDataBlockList results >>>> %s", results);
        logger.info(logText);
        return results;
    }
    
    public Duplicate removeDataBlockList(@RequestBody String requestJson) throws JSONException {
        logText = String.format("<<<<<removeDataBlockList>>>> %s", requestJson);
        logger.info(logText);
        AdminLoginDAO admin = new AdminLoginDAO(jdbcTemplateAPI);
        Duplicate result = admin.removeDataBlockList(requestJson);
        logText = String.format("<<<<<removeDataBlockList results >>>> %s", result);
        logger.info(logText);
        return result;
    }    
    
    public List<Member> getMemberList() throws JSONException {
        SpmGroupDao spmGroup = new SpmGroupDao(jdbcTemplateAPI);
        List<Member> results = spmGroup.getMember();
        logText = String.format("<<<<<getMemberList results >>>> %s", results);
        logger.info(logText);
        return results;
    }    
    
    public List<ViewApiUser> getViewApiUser(@RequestBody String requestJson) {
        List<ViewApiUser> viewApiObj = new ArrayList();
        JSONObject json = new JSONObject(requestJson);
        String aiCode = json.get("ai_code").toString();
        String userName = json.get(USER_NAME).toString();
        ViewApiUserService viewApiUser = new ViewApiUserService(jdbcTemplateAPI);
        try {
            viewApiObj = viewApiUser.getUserByAICodeAndUserId(aiCode, userName);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new ArrayList();
        }
        return viewApiObj;
    }    
}
