package com.arg.ccra.adminonline.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController implements ErrorController{
    private final String PATH = "/error";
    
        /*
     * Redirects all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
     */
    // @RequestMapping( method = {RequestMethod.OPTIONS, RequestMethod.GET }, value = "{_:^(?!index\\.html|adminapi|operationapi).*$}" )
    // public String forwardVueJSPaths() { 
    // 	// path = { "/api/**" }, 
    // 	// value = "{_:^(?!index\\.html|api).*$}"
    //     return "forward:/index.html"; 
    // } 


    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public String error() {
        return "forward:/index.html";
    }
}
