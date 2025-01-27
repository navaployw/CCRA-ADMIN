/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arg.ccra3.online.service;


import com.arg.ccra3.dao.repo.ApiCtrlRepository;
import com.arg.ccra3.model.ApiCtrl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kumpeep
 */
@Service
public class ApiCtrlService {
    @Autowired
    private ApiCtrlRepository apiCtrlRepository;
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private String logText = "";
    public Boolean allowCDITokenDuplicate(){
        List<ApiCtrl> allowCDITokenDup = apiCtrlRepository.findByCtrlType("CDIDup");
        if(!allowCDITokenDup.isEmpty()){
            logger.info("!allowCDITokenDup.isEmpty()");
            logText = String.format("allowCDITokenDup.get(0).getCtrlValue()::: %s", allowCDITokenDup.get(0).getCtrlValue());
            logger.info(logText);
            if(allowCDITokenDup.get(0).getCtrlValue().trim().equals("Y")){
                logger.info("Return true");
                return true;
            }
        }

        return false;
    }
    
     public String getFileDBConfigPath(){
        List<ApiCtrl> resultList = apiCtrlRepository.findByCtrlType("DBConfigPath");
        if(!resultList.isEmpty()){
            if(!resultList.get(0).getCtrlValue().trim().equals("")){
                return resultList.get(0).getCtrlValue();
            }
        }

        return "";
    }
     
    public String getCtrlValueByCtrlType(String value){
        logText = String.format("ctrlValue:value: %s", value);
        logger.info(logText);
        List<ApiCtrl> resultList = apiCtrlRepository.findByCtrlType(value);
        logText = String.format(">>>ResultList: %s", resultList);
        logger.info(logText);
        if(!resultList.isEmpty()){
            if(!resultList.get(0).getCtrlValue().trim().equals("")){
                logText = String.format(">>>CtrlValue: %s", resultList.get(0).getCtrlValue());
                logger.info(logText);
                return resultList.get(0).getCtrlValue();
            }
        }

        return "";
    } 
    
    public List<ApiCtrl> getCtrlValueByCtrlTypeList (String value){
        logText = String.format("ctrlValue:value: %s", value);
        logger.info(logText);
        List<ApiCtrl> resultList = apiCtrlRepository.findByCtrlType(value);
        logText = String.format(">>>ResultList: %s", resultList);
        logger.info(logText);
        return resultList;
    } 
     
}
