package com.arg.ccra.adminonline.utils;

public class GenericException extends Exception {
    private String errorCode = "";
 
    public GenericException() {
    }
 
    public GenericException(String message) {
       super(message);
    }
 
    public GenericException(String errCode, String message) {
       super(message);
       this.errorCode = errCode;
    }
 
    public GenericException(String message, Throwable cause) {
       super(message, cause);
    }
 
    public GenericException(String errCode, String message, Throwable cause) {
       super(message, cause);
       this.errorCode = errCode;
    }
 
    public GenericException(Throwable cause) {
       super(cause);
    }
 
    public String getMessage() {
       return this.errorCode.length() == 0 ? super.getMessage() : this.errorCode + ": " + super.getMessage();
    }
 
    public String getErrorCode() {
       return this.errorCode;
    }
 
    public String getMessageOnly() {
       return super.getMessage();
    }
 }
 