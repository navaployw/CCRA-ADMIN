/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arg.ccra.adminonline.services;


 
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.arg.ccra.adminonline.models.TrnJson;
import com.arg.ccra.adminonline.utils.SymmetricCipher;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class HttpTraceFilterRepositoryManager extends OncePerRequestFilter{
    @Autowired
    TrnJsonService trnJson;
    private final Logger loggerS = (Logger) LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ApiCtrlService apiCtrlService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String ctrlValue = apiCtrlService.getCtrlValueByCtrlType("encryptFlag");
            String logText = "";
            Boolean encryptFlag = false;
            if(ctrlValue.equals("Y")){
                encryptFlag = true;
            }
            ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(
                    response);
            ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(
                    request);
            try {
                filterChain.doFilter(wrappedRequest, wrappedResponse);
                logText =  String.format("aa.trnJsonID>>> %s", trnJson.getSaveObj());
                loggerS.info(logText);
                if(trnJson.getSaveObj()!=null){
                  TrnJson trnJsonObjResponse = trnJson.getSaveObj();
                  logText =  String.format("TrnJsonObjResponse:: %s", trnJsonObjResponse);
                  loggerS.info(logText);
                  logText =  String.format("statusCode:: %s", Long.parseLong(Integer.toString(response.getStatus())));
                  loggerS.info(logText);
                  logText =  String.format("getResponseBody(wrappedResponse).toString().length():: %s", getResponseBody(wrappedResponse).toString().length());
                  loggerS.info(logText);
                  if(encryptFlag){
                    SymmetricCipher cy = SymmetricCipher.getInstance();
                    trnJsonObjResponse.setJsonResponse(cy.encrypt(getResponseBody(wrappedResponse).toString()));
                  }else{
                    trnJsonObjResponse.setJsonResponse(getResponseBody(wrappedResponse).toString());
                  }
                  trnJsonObjResponse.setStatusCode(Long.parseLong(Integer.toString(response.getStatus())));
                  trnJson.saveJsonResponse(trnJsonObjResponse);
                }
            } catch (Exception ex) {
                String errorLog = String.format("Error %s", ex);
                loggerS.error(errorLog);
            } finally {
                wrappedResponse.copyBodyToResponse();
            }
    }

        protected StringBuilder getResponseBody(
                ContentCachingResponseWrapper wrappedResponse) {
            try {
                if (wrappedResponse.getContentSize() <= 0) {
                    return null;
                }
                byte[] srcBytes = wrappedResponse.getContentAsByteArray();
                Charset charset = Charset.forName( wrappedResponse.getCharacterEncoding());
                CharsetDecoder decoder = charset.newDecoder();
                ByteBuffer srcBuffer = ByteBuffer.wrap(srcBytes);
                CharBuffer resBuffer = decoder.decode(srcBuffer);
                return new StringBuilder(resBuffer);
            } catch (CharacterCodingException e) {
                String errorLog = String.format("Error %s", trnJson.getSaveObj());
                loggerS.error(errorLog);
                return null;
            }
        }
        
      
}
