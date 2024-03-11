package com.arg.ccra.adminonline.utils;

public class GenericRuntimeException extends RuntimeException {
    private String errorCode = "";
 
    public GenericRuntimeException() {
    }
 
    public GenericRuntimeException(String message) {
       super(message);
    }
 
    public GenericRuntimeException(String errCode, String message) {
       super(message);
       this.errorCode = errCode;
    }
 
    public GenericRuntimeException(String message, Throwable cause) {
       super(message, cause);
    }
 
    public GenericRuntimeException(String errCode, String message, Throwable cause) {
       super(message, cause);
       this.errorCode = errCode;
    }
 
    public GenericRuntimeException(Throwable cause) {
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