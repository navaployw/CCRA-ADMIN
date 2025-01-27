
package com.arg.ccra3.online.util;

import ch.qos.logback.classic.Logger;
import com.arg.ccra3.model.CtrlKey;
import com.arg.ccra3.model.MessageError;
import com.arg.ccra3.model.ResponseModel;
import com.arg.ccra3.model.TrnJson;
import com.arg.ccra3.model.security.ViewApiUser;
import com.arg.ccra3.online.form.ReportAPIRequest;

import com.arg.ccra3.online.service.AesEcbEncryptDecrypt;
import com.arg.ccra3.online.service.CtrlKeyService;
import com.arg.ccra3.online.service.MessageErrorService;
import com.arg.ccra3.online.service.TrnJsonService;
import com.arg.ccra3.online.service.ViewApiUserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import org.json.JSONObject;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestAttributes;

public class AuthenUtil {
    private final TrnJsonService trnJson;
    private final MessageErrorService messageErrorService;
    private final ViewApiUserService userService;
    private final CtrlKeyService ctrlKey;
    
    private final Logger logger = (Logger) LoggerFactory.getLogger(AuthenUtil.class);
    
    public AuthenUtil(
        TrnJsonService trnJson, 
        MessageErrorService messageErrorService,
        ViewApiUserService userService,
        CtrlKeyService ctrlKey){
        if(trnJson == null)
            logger.info("trnJson is null-------------------------------------------");
        if(messageErrorService == null)
            logger.info("messageErrorService is null-------------------------------------------");
        if(userService == null)
            logger.info("userService is null-------------------------------------------");
        if(ctrlKey == null)
            logger.info("ctrlKey is null-------------------------------------------");
        
        this.trnJson = trnJson;
        this.messageErrorService = messageErrorService;
        this.userService = userService;
        this.ctrlKey = ctrlKey;
    }
    
    
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    
    public ResponseEntity<Object> doAfterAuthen(
            ReportAPIRequest requestJson, 
            RequestAttributes requestAttr, 
            Function<ReportAPIRequest, ResponseEntity<Object>> func){
        try{
            requestJson.setUserID(tryGettingUserID(requestJson, requestAttr));
            requestJson.setMatchTranId(18147l);//dummy fix me
            return func.apply(requestJson);
        }
        catch(IllegalArgumentException e){
            logger.error(e.getMessage());
            return handleError(e);
        }
    }
    private ResponseEntity<Object> handleError(IllegalArgumentException e){
        String code = e.getMessage();
        ResponseModel res = new ResponseModel();
        List<MessageError> message = messageErrorService.getMessageByCode(code);
        if(!message.isEmpty()){
            res.setCode(message.get(0).getErrorCode());
            res.setMessage(message.get(0).getErrorMessage());
        }else{
            res.setCode(code);
            switch(code){
                case "00040" -> res.setMessage("Access token expired.");
                case "00042" -> res.setMessage("CDI token expired.");
                case "00043" -> res.setMessage("Invalid CDI token signature.");
                case "00045" -> res.setMessage("The company ID of CDI token is not equal to the DUNS number of the request.");
                case "00046" -> res.setMessage("The AI code of CDI token is invalid.");
                case "00047" -> res.setMessage("The AI code of CCRA-API token is not equal to the AI code of the request.");
            }
        }
        return  ResponseEntity.status(401).body(res);
    }
    
    public String tryGettingUserID(ReportAPIRequest requestJson, RequestAttributes requestAttr) throws JSONException {
        logger.info("tryGettingUserID");
        String authorization = ((ServletRequestAttributes) requestAttr).getRequest().getHeader("Authorization");
        String accessToken = "";
        if (authorization != null && authorization.toLowerCase().startsWith("bearer")) 
            accessToken = authorization.split("Bearer ")[1];
        
        String aiJSON = requestJson.getAiCode();
        String loggerText = String.format("aiJSON>>> %s", aiJSON);
        logger.info(loggerText);
        String cdiToken = requestJson.getCdiToken();
        String companyIDjson = requestJson.getCompanyID().toString();
        
        String CDITokenBody = deCodeCDIToken(cdiToken);
        JSONObject jsonObjCDI = new JSONObject(CDITokenBody);

        String CCRATokenBody = deCodeCCRAToken(accessToken);
        JSONObject jsonObjCCRA = new JSONObject(CCRATokenBody);
        
        String aiCCRA = jsonObjCCRA.get("ai_code").toString();
        loggerText = String.format("AI_CODE_CCRA>>> %s", jsonObjCCRA.get("ai_code"));
        logger.info(loggerText);
        String userId = jsonObjCCRA.get("client_id").toString();
        loggerText = String.format("Username>>> %s", userId);
        logger.info(loggerText);
        String aiCDI = jsonObjCDI.get("ai_code").toString();
        loggerText = String.format("AI_CODE_CDI>>> %s", jsonObjCDI.get("ai_code"));
        logger.info(loggerText);
        String companyCDI = jsonObjCDI.get("company_id").toString();
        loggerText = String.format("companyCDI>>> %s", jsonObjCDI.get("company_id"));
        logger.info(loggerText);
        String jti = jsonObjCDI.get("jti").toString();
        loggerText = String.format("jti>>> %s", jti);
        logger.info(loggerText);
        Date expCDI = new Date(Long.valueOf(jsonObjCDI.get("exp").toString()) * 1000);
        loggerText = String.format("expCDI>>> %s", jsonObjCDI.get("exp"));
        logger.info(loggerText);
        loggerText = String.format("expCDI>>> %s", expCDI);
        logger.info(loggerText);
        Date expCCRA = new Date(Long.valueOf(jsonObjCCRA.get("exp").toString()) * 1000);
        loggerText = String.format("expCCRA>>> %s", expCCRA);
        logger.info(loggerText);
        
        /*boolean isValidSignature = false;
        try {
            isValidSignature = verifyToken(cdiToken);
        } catch (InvalidKeySpecException ex) {
            logger.error("Exception::"+ex);
        }        
        logger.info("isValidSignature>>>" + isValidSignature); 
        if (!isValidSignature) 
            save_trnJson_error("00043", aiJSON, aiCDI);
        
        if (!aiCCRA.equals(aiJSON)) 
            save_trnJson_error("00047", aiJSON, aiCDI);
        if (!aiCDI.equals(aiJSON)) 
            save_trnJson_error("00046", aiJSON, aiCDI);
        if (!companyCDI.equals(companyIDjson))
            save_trnJson_error("00045", aiJSON, aiCDI);
        if (expCCRA.before(new Date()))
            save_trnJson_error("00040", aiJSON, aiCDI);
        if (expCDI.before(new Date())) 
            save_trnJson_error("00042", aiJSON, aiCDI);*/
        
        try{
            List<ViewApiUser> userList = userService.getUserByAICode(aiJSON);
            if(userList.size()== 1){                
                AesEcbEncryptDecrypt encryptObject = new AesEcbEncryptDecrypt();
                AesEcbEncryptDecrypt.setKey(userList.get(0).getSecretKey());
                userId = encryptObject.decrypt(userId);
                /*logger.info("userList:"+userList.get(0));
                if(userId.equals(userList.get(0).getUserID())){
                    TrnJson trnJsonObjRequest = new TrnJson();
                    trnJsonObjRequest.setModule(2L);
                    trnJsonObjRequest.setAccessToken(accessToken);
                    trnJsonObjRequest.setRequestTime(new Date());
                    trnJsonObjRequest.setJsonRequest("{request_url:'get_ccra_report',ai_code:" + aiCDI + "}");
                    TrnJson trnJsonObjResponse = trnJson.saveJsonRrequest(trnJsonObjRequest);
                    logger.info("trnJsonObjResponse::"+trnJsonObjResponse);
                    trnJsonObjResponse.setResponseTime(new Date());
                    trnJsonObjResponse.setStatusCode(200L);
                    trnJsonObjResponse.setaId(Long.parseLong(userList.get(0).getaID().toString()));
                    trnJsonObjResponse.setuId(Long.parseLong(userList.get(0).getuID().toString()));
                    trnJsonObjResponse.setGroupId(Long.parseLong(userList.get(0).getGroupID()));
                    
                    cdiTokenObj.setAiCode(aiJSON);
                    cdiTokenObj.setCdiToken(cdiToken);
                    cdiTokenObj.setPayload(CDITokenBody);
                    cdiTokenObj.setJti(jti);
                    cdiTokenObj.setExpireTime(expCDI);
                    cdiTokenObj.setCreateTime(new Date());
                    cdiTokenObj.setaId(Long.parseLong(userList.get(0).getaID().toString()));
                    cdiTokenObj.setuId(Long.parseLong(userList.get(0).getuID().toString()));
                    cdiTokenObj.setTranTime(new Date());
                    
                    trnJson.saveJsonResponse(trnJsonObjResponse);
                }
                else
                    save_trnJson_error("00041", aiJSON, aiCDI);*/
            }
        }catch(Exception e){
            logger.info("Exception::"+e);
        }
        
        return userId;
    }

    public String deCodeCCRAToken(String jwtToken) {
        Base64 base64Url = new Base64(true);
        logger.info("------------ Decode JWT ------------");
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        String base64EncodedSignature = split_string[2];

        logger.info("~~~~~~~~~ JWT Header ~~~~~~~");

        String header = new String(base64Url.decode(base64EncodedHeader));
        logger.info("JWT Header : " + header);

        logger.info("~~~~~~~~~ JWT Body ~~~~~~~");
        String body = new String(base64Url.decode(base64EncodedBody));
        logger.info("JWT Body : " + body);

        logger.info("JWT Signature : " + base64EncodedSignature);
        return body;
    }

    public String deCodeCDIToken(String cdiToken) {
        Base64 base64Url = new Base64(true);
        String jwtToken = cdiToken;
        logger.info("------------ Decode JWT cdiToken ------------");
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        String base64EncodedSignature = split_string[2];

        logger.info("~~~~~~~~~ JWT Header cdiToken ~~~~~~~");

        String header = new String(base64Url.decode(base64EncodedHeader));
        logger.info("JWT Header : " + header);

        logger.info("~~~~~~~~~ JWT Body cdiToken~~~~~~~");
        String body = new String(base64Url.decode(base64EncodedBody));
        logger.info("JWT Body : " + body);

        logger.info("JWT Signature : " + base64EncodedSignature);
        return body;
    }

    public boolean verifyToken(String token) throws InvalidKeySpecException {
        try {
//            String publicKey = "ZsKDkxQUGNFebdG6645Vj6bZvOOt6Heh";
            List<CtrlKey> keyInfo = ctrlKey.getKey();
            logger.info("keyInfo::"+keyInfo);
            String publicKey = keyInfo.get(0).getCtrlValue();
            logger.info("publicKey::"+publicKey);
            Algorithm algorithm = Algorithm.HMAC256(publicKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    //.withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            logger.info("jwt>>>"+jwt);
            return true;
        } catch (Exception ex) {
           logger.error("Error:"+ex);
           return false;
        }
    }
    
    private void save_trnJson_error(String code, String aiJSON, String aiCDI){
        try{
            TrnJson trnJsonObjRequest = new TrnJson();
            logger.info("error:aiJSON:"+aiJSON);
            trnJsonObjRequest.setModuleNo(2L);
            trnJsonObjRequest.setRequestTime(new Date());
            trnJsonObjRequest.setJsonRequest("{request_url:'get_ccra_report',ai_code:" + aiCDI + "}");
            TrnJson trnJsonObjResponse = trnJson.saveJsonRrequest(trnJsonObjRequest);
            logger.info("trnJsonObjResponse::"+trnJsonObjResponse);
            trnJsonObjResponse.setResponseTime(new Date());
            trnJsonObjResponse.setErrorCode(code);
            trnJsonObjResponse.setStatusCode(401L);
            trnJson.saveJsonResponse(trnJsonObjResponse);
        }catch(Exception e){
            logger.info("Exception::"+e);
        }
        throw new IllegalArgumentException(code);
    }
}