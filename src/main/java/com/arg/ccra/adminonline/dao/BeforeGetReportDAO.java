/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arg.ccra.adminonline.dao;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.arg.ccra.adminonline.models.MessageError;
import com.arg.ccra.adminonline.models.security.Company;
import com.arg.ccra.adminonline.models.security.SpmTransaction;

public class BeforeGetReportDAO {

    private final JdbcTemplate jdbcTemplateApi;
    private final Logger logger = (Logger) LoggerFactory.getLogger(BeforeGetReportDAO.class);
    private String infoLog = "";
    public BeforeGetReportDAO(JdbcTemplate jdbcTemplateApi){
        this.jdbcTemplateApi = jdbcTemplateApi;
    }
 
    public List<Company> getCompanyByDunsNo(BigDecimal company) {
        logger.info("getCompanyByDunsNo>>>");
        String sql = "exec get_customerByDunsNo null, null, ?";
        Object[] params = new Object[]{company};
        List<Company> results = this.jdbcTemplateApi.query(sql, BeanPropertyRowMapper.newInstance(Company.class), params);
        infoLog = String.format("resultsCompany>>>>> %s", results);
        logger.info(infoLog);
        return results;
    }

    public List<MessageError> getErrorMessageByErrorCodeNative(String code) {
        logger.info("getErrorMessageByErrorCodeNative>>>");
        String sql = "select * from API_MESSAGE_ERR where ERROR_CODE = ?";
        Object[] params = new Object[]{code};
        List<MessageError> results = this.jdbcTemplateApi.query(sql, BeanPropertyRowMapper.newInstance(MessageError.class), params);
        infoLog = String.format("checkOrderChinese>>>>> %s", results);
        logger.info(infoLog);
        return results;

    }

    public List<SpmTransaction> insertApiSpmTransaction(Integer sessionId, BigDecimal uid, BigDecimal groupAiId, BigDecimal groupId,Integer expense) {
        logger.info("insertApiSpmTransaction>>>");
        String sql = "INSERT INTO API_SPM_TRANSACTION VALUES(?,?,?,?,202,null,'A',?,?,getDate(),?,getDate()); SELECT * FROM API_SPM_TRANSACTION WHERE TRANSACTIONID = SCOPE_IDENTITY()";
        Object[] params = new Object[]{sessionId, uid, groupAiId, groupId,expense, uid, uid};
        List<SpmTransaction> results = this.jdbcTemplateApi.query(sql, BeanPropertyRowMapper.newInstance(SpmTransaction.class), params);
        logger.info("return insertApiSpmTransaction>>>>>" + results);
        
        return results;
    }

    public void insertApiProductDeliver (Integer transactionId, String dunsNo,Integer reasonCode,String aiRefCode1,String aiRefCode2,String aiRefCode3,
        Integer objectId,BigDecimal uId,BigDecimal groupAiId,BigDecimal groupId) {
        logger.info("insertApiProductDeliver>>>");
        String sql = "INSERT INTO API_PRODUCTDELIVER OUTPUT Inserted.TRANSACTIONID VALUES(?,NULL,NULL,0,'A',?,?,?,?,?,?,0,NULL,NULL,getDate(),NULL,'E',?,?,?,?,0,NULL,1,1,0)";
        Object[] params = new Object[]{transactionId, dunsNo, reasonCode, aiRefCode1, aiRefCode2, aiRefCode3,objectId,uId,groupAiId,groupId,transactionId};
        jdbcTemplateApi.query(sql, BeanPropertyRowMapper.newInstance(SpmTransaction.class), params);
    }

}
