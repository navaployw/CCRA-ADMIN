/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arg.ccra.adminonline.models.report;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AllUsageReportModel {
    private String zipFileName;
    private List<UsageReportModel> usageSummary;
    private List<DischargeReportModel> dischargeReport;
    private List<UsageTransactionReport> transactionReport;

    public AllUsageReportModel(List<UsageReportModel> usageSummary, List<DischargeReportModel> dischargeReport, List<UsageTransactionReport> transactionReport) {
        this.usageSummary = usageSummary;
        this.dischargeReport = dischargeReport;
        this.transactionReport = transactionReport;
    }


    
}
