
package com.arg.ccra.adminonline.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.arg.ccra.adminonline.services.CtrlKeyService;
import com.arg.ccra.adminonline.services.MessageErrorService;
import com.arg.ccra.adminonline.services.TrnJsonService;
import com.arg.ccra.adminonline.services.ViewApiUserService;
import com.arg.ccra.adminonline.utils.AuthenUtil;

@RestController
public class AbstractCtrl {

    
    public AbstractCtrl() {
    }
    
    
    public static TrnJsonService trnJson = new TrnJsonService();
    public static MessageErrorService messageErrorService = new MessageErrorService();
    public static ViewApiUserService userService = new ViewApiUserService();
    public static CtrlKeyService ctrlKey = new CtrlKeyService();
    
    protected static AuthenUtil authenUtil = new AuthenUtil(trnJson, messageErrorService, userService, ctrlKey);
}
