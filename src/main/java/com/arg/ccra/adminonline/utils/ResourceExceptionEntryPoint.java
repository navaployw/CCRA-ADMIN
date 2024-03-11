/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arg.ccra.adminonline.utils;

import com.arg.ccra.adminonline.models.MessageError;
import com.arg.ccra.adminonline.models.ResponseModel;
import com.arg.ccra.adminonline.services.MessageErrorService;
import com.arg.ccra.adminonline.services.TrnJsonService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
@Component("ResourceExceptionEntryPoint")
public class ResourceExceptionEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    TrnJsonService trnJson;
    @Autowired
    private MessageErrorService messageErrorService;

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    String userId;
    String password;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException, ServletException {
        logger.info(">>>Resource authen error<<<<");
        List<MessageError> message = messageErrorService.getMessageByCode("00040");
        ResponseModel res = new ResponseModel();
        res.setCode(message.get(0).getErrorCode());
        res.setMessage(message.get(0).getErrorMessage());
        final Map<String, Object> mapBodyException = new LinkedHashMap<>();
        mapBodyException.put("error_code", message.get(0).getErrorCode());
        mapBodyException.put("error_message",message.get(0).getErrorMessage());
        response.setContentType("application/json");
        response.setStatus(message.get(0).getStatusCode());
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), mapBodyException);

    }



}
