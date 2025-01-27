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
public class UsageReportModel {
    private String reportDate;
    private String requestedBy;
    private String reportPeriod;
    private String memberCode;
    private String memberName;
    private Long productCode;
    private String product;
    private Integer usage;

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
        return memberCode==null?"":memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberName() {
        return memberName==null?"":memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public String getProduct() {
        return product==null?"":product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getUsage() {
        return usage;
    }

    public void setUsage(Integer usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "UsageReportModel{" + "reportDate=" + reportDate + ", requestedBy=" + requestedBy + ", reportPeriod=" + reportPeriod + ", memberCode=" + memberCode + ", memberName=" + memberName + ", productCode=" + productCode + ", product=" + product + ", usage=" + usage + '}';
    }


}
