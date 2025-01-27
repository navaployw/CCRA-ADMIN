/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arg.ccra3.model.report;

import java.util.Date;

/**
 *
 * @author kumpeep
 */
public class DischargeReportModel {
    private String reportDate;
    private String requestedBy;
    private String reportPeriod;
    private String memberCode;
    private String memberName;
    private String   reportOrderedDateTime;
    private String hkbrc;
    private String hkci;
    private String otherRegistrationIncorporationNumber;
    private String placeOfRegistrationIncorporation;
    private String customerNumber;
    private String locationBranchID;
    private String accountManagerCode;
    private String reasonCode;
    private String userId;
    private Long productCode;
    private String productName;
    private String reportRefNo;
    private String aiRefCode1;
    private String aiRefCode2;
    private String aiRefCode3;
    private Date dischargeDate;
    private String status;

     public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getRequestedBy() {
        return requestedBy==null?"":requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getReportPeriod() {
        return reportPeriod==null?"":reportPeriod;
    }

    public void setReportPeriod(String reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode==null?"":memberCode;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName==null?"":memberName;
    }

    public String getReportOrderedDateTime() {
        return reportOrderedDateTime;
    }

    public void setReportOrderedDateTime(String reportOrderedDateTime) {
        this.reportOrderedDateTime = reportOrderedDateTime;
    }

    public String getHkbrc() {
        return hkbrc==null?"":hkbrc;
    }

    public void setHkbrc(String hkbrc) {
        this.hkbrc = hkbrc;
    }

    public String getHkci() {
        return hkci==null?"":hkci;
    }

    public void setHkci(String hkci) {
        this.hkci = hkci;
    }

    public String getOtherRegistrationIncorporationNumber() {
        return otherRegistrationIncorporationNumber==null?"":otherRegistrationIncorporationNumber;
    }

    public void setOtherRegistrationIncorporationNumber(String otherRegistrationIncorporationNumber) {
        this.otherRegistrationIncorporationNumber = otherRegistrationIncorporationNumber;
    }

    public String getPlaceOfRegistrationIncorporation() {
        return placeOfRegistrationIncorporation==null?"":placeOfRegistrationIncorporation;
    }

    public void setPlaceOfRegistrationIncorporation(String placeOfRegistrationIncorporation) {
        this.placeOfRegistrationIncorporation = placeOfRegistrationIncorporation;
    }

    public String getCustomerNumber() {
        return customerNumber==null?"":customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getAccountManagerCode() {
        return accountManagerCode==null?"":accountManagerCode;
    }

    public void setAccountManagerCode(String accountManagerCode) {
        this.accountManagerCode = accountManagerCode;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getUserId() {
        return userId==null?"":userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName==null?"":productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getReportRefNo() {
        return reportRefNo==null?"":reportRefNo;
    }

    public void setReportRefNo(String reportRefNo) {
        this.reportRefNo = reportRefNo;
    }

    public String getAiRefCode1() {
        return aiRefCode1==null?"":aiRefCode1;
    }

    public void setAiRefCode1(String aiRefCode1) {
        this.aiRefCode1 = aiRefCode1;
    }

    public String getAiRefCode2() {
        return aiRefCode2==null?"":aiRefCode2;
    }

    public void setAiRefCode2(String aiRefCode2) {
        this.aiRefCode2 = aiRefCode2;
    }

    public String getAiRefCode3() {
        return aiRefCode3==null?"":aiRefCode3;
    }

    public void setAiRefCode3(String aiRefCode3) {
        this.aiRefCode3 = aiRefCode3;
    }

    public String getLocationBranchID() {
        return locationBranchID==null?"":locationBranchID;
    }

    public void setLocationBranchID(String locationBranchID) {
        this.locationBranchID = locationBranchID;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getStatus() {
        return status==null?"":status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DischargeReportModel{" + "reportDate=" + reportDate + ", requestedBy=" + requestedBy + ", reportPeriod=" + reportPeriod + ", memberCode=" + memberCode + ", memberName=" + memberName + ", reportOrderedDateTime=" + reportOrderedDateTime + ", hkbrc=" + hkbrc + ", hkci=" + hkci + ", otherRegistrationIncorporationNumber=" + otherRegistrationIncorporationNumber + ", placeOfRegistrationIncorporation=" + placeOfRegistrationIncorporation + ", customerNumber=" + customerNumber + ", locationBranchID=" + locationBranchID + ", accountManagerCode=" + accountManagerCode + ", reasonCode=" + reasonCode + ", userId=" + userId + ", productCode=" + productCode + ", productName=" + productName + ", reportRefNo=" + reportRefNo + ", aiRefCode1=" + aiRefCode1 + ", aiRefCode2=" + aiRefCode2 + ", aiRefCode3=" + aiRefCode3 + ", dischargeDate=" + dischargeDate + ", status=" + status + '}';
    }





}
