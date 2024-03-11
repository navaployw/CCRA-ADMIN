/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arg.ccra.adminonline.controllers;


import java.util.List;
import org.json.JSONException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arg.ccra.adminonline.models.ApiCtrl;
import com.arg.ccra.adminonline.models.CountDataBlock;
import com.arg.ccra.adminonline.models.DataBlock;
import com.arg.ccra.adminonline.models.Duplicate;
import com.arg.ccra.adminonline.models.LoginData;
import com.arg.ccra.adminonline.models.Member;
import com.arg.ccra.adminonline.models.ResponseModel;
import com.arg.ccra.adminonline.models.security.ViewApiUser;
import com.arg.ccra.adminonline.services.AdminService;
import com.arg.ccra.adminonline.services.ApiCtrlService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@RestController
@RequestMapping(path = "api/admin")
public class AdminCtrl {


    @Autowired
    private AdminService adminService;
    @Autowired
    private ApiCtrlService apiCtrlService;
    @RequestMapping(value = "/checkLogin", method = {RequestMethod.POST})
    public LoginData checkLogin(@RequestBody String requestJson, HttpServletRequest req, HttpServletResponse res) throws Exception {
        return adminService.checkLoginService(requestJson,  res, req);
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    public ResponseModel logout(@RequestBody String requestJson) throws JSONException {
        return adminService.logoutService(requestJson);
    }

    @RequestMapping(value = "/getDataBlockList", method = {RequestMethod.POST})
    public List<DataBlock> getDataBlockList(@RequestBody String requestJson) throws JSONException {
        return adminService.getDataBlockList(requestJson);
    }

    @RequestMapping(value = "/insertUpdateDataBlockList", method = {RequestMethod.POST})
    public Duplicate insertUpdateDataBlockList(@RequestBody String requestJson) throws JSONException {
        return adminService.insertUpdateDataBlockList(requestJson);
    }

    @RequestMapping(value = "/beforeRemoveDataBlockList", method = {RequestMethod.POST})
    public List<CountDataBlock> beforeRemoveDataBlockList(@RequestBody String requestJson) throws JSONException {
        return adminService.beforeRemoveDataBlockList(requestJson);
    }

    @RequestMapping(value = "/removeDataBlockList", method = {RequestMethod.POST})
    public Duplicate removeDataBlockList(@RequestBody String requestJson) throws JSONException {
        return adminService.removeDataBlockList(requestJson);
    }

    @RequestMapping(value = "/getMemberList", method = {RequestMethod.GET})
    public List<Member> getMemberList() throws JSONException {
        return adminService.getMemberList();
    }

    @RequestMapping(value = "/getViewApiUser", method = {RequestMethod.POST})
    @ResponseBody
    public List<ViewApiUser> getViewApiUser(@RequestBody String requestJson) {
       return adminService.getViewApiUser(requestJson);
    }
    
    @RequestMapping(value = "/getVersionNo", method = {RequestMethod.GET})
    @ResponseBody
    public List<ApiCtrl> getVersionNo() {
       List<ApiCtrl> versionNoList = apiCtrlService.getCtrlValueByCtrlTypeList("AppVersion");
       return versionNoList;
    }

}
