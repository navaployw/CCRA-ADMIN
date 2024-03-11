package com.arg.ccra.adminonline.controllers;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arg.ccra.adminonline.services.UserService;
        
@RestController
@RequestMapping(path = "api/user")        
public class UserCtrl {
    private final Logger logger = (Logger) LoggerFactory.getLogger(UserCtrl.class);
    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/searchWithUserId/{userId}")
    public List<Map<String, Object>> searchWithUserId(@PathVariable(required = false) String userId){
        return userService.searchWithUserId(userId);
    }
    @GetMapping(value = "/searchWithUserId/")//WHY???????????????????????
    public List<Map<String, Object>> searchWithUserIdNth(){
        return userService.searchWithUserId(null);
    }
    
    @GetMapping(value = "/checkUserDuplicateAI/{uid}/{gid}/{gaiid}")
    public ResponseEntity<Object> checkUserDuplicateAI(
        @PathVariable long uid,
        @PathVariable long gid,
        @PathVariable long gaiid
    ){
        return userService.checkUserDuplicateAI(uid, gid, gaiid);
    }
    
    @GetMapping(value = "/getAPIUserList")
    public List<Object> getAPIUserList(){
        return userService.getAPIUserList();
    }
    
    @GetMapping(value = "/getUserDetailByAID/{aID}")
    public Object getUserDetailByAID(@PathVariable long aID){
        return userService.getUserDetailByAID(aID);
    }
    
    @GetMapping(value = "/getListDataBlock")
    public List<Object> getListDataBlock(){
        return userService.getListDataBlock();
    }
    
    @GetMapping(value = "/getListDataBlockByUID/{uID}")
    public List<Long> getListDataBlockByUID(@PathVariable long uID){
        return userService.getListDataBlockByUID(uID);
    }
    
    @PostMapping(value = "/saveUserInfo")
    public ResponseEntity<Object> saveUserInfo(@RequestBody Map<String, Object> user){
        logger.info("UserCtrl:User:"+user);
        return userService.saveUserInfo(user);
    }
    
    @DeleteMapping(value = "/deleteUserByAIDandUID/{by}/{aID}/{uID}")
    public ResponseEntity<Object> deleteUserByAIDandUID(
        @PathVariable String by,
        @PathVariable long aID,
        @PathVariable long uID
    ){
        return userService.deleteUserByAIDandUID(by, aID, uID);
    }
    
    @GetMapping(value = "/isTokenActiveByAID/{aID}")
    public boolean isTokenActiveByAID(@PathVariable long aID){
        return userService.isTokenActiveByAID(aID);
    }
}
