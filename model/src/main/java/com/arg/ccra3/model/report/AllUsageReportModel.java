/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arg.ccra3.model.report;

import java.util.List;

/**
 *
 * @author kumpeep
 */
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

    public String getZipFileName() {
        return zipFileName;
    }

    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }
    
    public List<UsageReportModel> getUsageSummary() {
        return usageSummary;
    }

    public void setUsageSummary(List<UsageReportModel> usageSummary) {
        this.usageSummary = usageSummary;
    }

    public List<DischargeReportModel> getDischargeReport() {
        return dischargeReport;
    }

    public void setDischargeReport(List<DischargeReportModel> dischargeReport) {
        this.dischargeReport = dischargeReport;
    }

    public List<UsageTransactionReport> getTransactionReport() {
        return transactionReport;
    }

    public void setTransactionReport(List<UsageTransactionReport> transactionReport) {
        this.transactionReport = transactionReport;
    }

    @Override
    public String toString() {
        return "AllUsageReportModel{" + "zipFileName=" + zipFileName + ", usageSummary=" + usageSummary + ", dischargeReport=" + dischargeReport + ", transactionReport=" + transactionReport + '}';
    }

    
}
