/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arg.ccra.adminonline.services;

import java.sql.SQLException;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arg.ccra.adminonline.models.TrnJson;
import com.arg.ccra.adminonline.repositorys.TrnJsonRepository;

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
