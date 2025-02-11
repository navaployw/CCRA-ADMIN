/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arg.ccra3.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author navaployw
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel {
    
    String message;
    String code;
    String data;

    public ResponseModel() {
    }

    public ResponseModel(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return '{' + "message=" + message + ", code=" + code + ", data=" + data + '}';
    }

    
    
}
