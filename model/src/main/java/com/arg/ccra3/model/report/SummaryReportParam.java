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
public class SummaryReportParam {
    private String uId;
    private String userId;
    private Date reportDate;
    private Date startDate;
    private Date endDate;
    private String whereCase;
    private String groupAiId;
    private String groupId;
    private String monthlyPlan;
    private String sortPri;
    private String sortSec;
    private String sortMem;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date st) {
        this.startDate = st;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date en) {
        this.endDate = en;
    }

    public String getWhereCase() {
        return whereCase;
    }

    public void setWhereCase(String whereCase) {
        this.whereCase = whereCase;
    }

    public String getGroupAiId() {
        return groupAiId;
    }

    public void setGroupAiId(String groupAiId) {
        this.groupAiId = groupAiId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMonthlyPlan() {
        return monthlyPlan;
    }

    public void setMonthlyPlan(String monthlyPlan) {
        this.monthlyPlan = monthlyPlan;
    }

    public String getSortPri() {
        return sortPri;
    }

    public void setSortPri(String sortPri) {
        this.sortPri = sortPri;
    }

    public String getSortSec() {
        return sortSec;
    }

    public void setSortSec(String sortSec) {
        this.sortSec = sortSec;
    }

    public String getSortMem() {
        return sortMem;
    }

    public void setSortMem(String sortMem) {
        this.sortMem = sortMem;
    }

    @Override
    public String toString() {
        return "SummaryReportParam{" + "uId=" + uId + ", userId=" + userId + ", reportDate=" + reportDate + ", startDate=" + startDate + ", endDate=" + endDate + ", whereCase=" + whereCase + ", groupAiId=" + groupAiId + ", groupId=" + groupId + ", monthlyPlan=" + monthlyPlan + ", sortPri=" + sortPri + ", sortSec=" + sortSec + ", sortMem=" + sortMem + '}';
    }

 



 
    
    
}
