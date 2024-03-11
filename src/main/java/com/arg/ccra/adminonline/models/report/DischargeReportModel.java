/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arg.ccra.adminonline.models.report;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DischargeReportModel {
    private String reportDate;
    private String requestedBy;
    private String reportPeriod;
    private String memberCode;
    private String memberName;
    private String reportOrderedDateTime;
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
}
