/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arg.ccra3.online.service;



import com.arg.ccra3.model.TrnJson;
import com.arg.ccra3.dao.repo.TrnJsonRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author navaployw
 */
@Service
public class TrnJsonService {
    @Autowired
    private TrnJsonRepository trnJsonRepository;
    TrnJson objSave;
    private final Logger logger = (Logger) LoggerFactory.getLogger(TrnJsonService.class);
    
    
    public TrnJson saveJsonRrequest(TrnJson data) throws SQLException {

        trnJsonRepository.save(data);

        return data;
    }

     public TrnJson saveJsonResponse(TrnJson data) throws SQLException {
        objSave = data;
        logger.info("JSONResponse:");
        logger.info(data.getJsonResponse());
        logger.info("ResponseTime:");
        logger.info(data.getResponseTime().toString());
        logger.info("StatusCode:");
        logger.info(data.getStatusCode().toString());
        logger.info("ErrorCode:");
        logger.info(data.getErrorCode());
        logger.info("TrnApiId:");
        logger.info(data.getTrnApiId().toString());

        trnJsonRepository.save(data);
        return data;
    }
     public TrnJson getSaveObj(){
         return objSave;
     } 
}
