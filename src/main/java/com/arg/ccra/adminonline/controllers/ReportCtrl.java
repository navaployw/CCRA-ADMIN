package com.arg.ccra.adminonline.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arg.ccra.adminonline.models.report.AllUsageReportModel;
import com.arg.ccra.adminonline.models.report.DischargeReportModel;
import com.arg.ccra.adminonline.models.report.SummaryReportParam;
import com.arg.ccra.adminonline.models.report.UsageReportModel;
import com.arg.ccra.adminonline.models.report.UsageTransactionReport;
import com.arg.ccra.adminonline.services.ReportService;

@RestController
@RequestMapping(path = "api/report")
public class ReportCtrl extends AbstractCtrl {

    @Autowired
    @Lazy
    private ReportService reportService;
    private final Logger logger = (Logger) LoggerFactory.getLogger(ReportCtrl.class);
    private String infoLog = "";
    @RequestMapping(value = "/get_report_usage_sum", method = {RequestMethod.POST})
    @ResponseBody
    public List<UsageReportModel> getUsageSummaryReport(@RequestBody SummaryReportParam requestJson) {
        logger.info(">>>>getReportUsageSummary<<<<");
        infoLog = String.format("Request:param:: %s", requestJson);
        logger.info(infoLog);
        return reportService.getUsageSummaryReport(requestJson);
    }

    @RequestMapping(value = "/get_report_usage_all", method = {RequestMethod.POST})
    @ResponseBody
    public AllUsageReportModel getUsageReportAll(@RequestBody SummaryReportParam requestJson) {
        logger.info(">>>>getUsageSummaryAll<<<<");
        infoLog = String.format("Request:param:: %s", requestJson);
        logger.info(infoLog);
        infoLog = String.format("startDate::  %s", new SimpleDateFormat("dd/MM/yyyy").format(requestJson.getStartDate()));
        logger.info(infoLog);
        infoLog = String.format("endDate:: %s", new SimpleDateFormat("dd/MM/yyyy").format(requestJson.getEndDate()));
        logger.info(infoLog);
        infoLog = String.format("monthlyPlan::  %s", requestJson.getMonthlyPlan());
        logger.info(infoLog);
        String memberId = requestJson.getMonthlyPlan()==null||requestJson.getMonthlyPlan().equals("")?"A":requestJson.getMonthlyPlan();
        String fileName = getFileNameZip("13",memberId);
        logger.info("FileName:: " );
        logger.info(fileName);
        List<UsageReportModel> usageSum = reportService.getUsageSummaryReport(requestJson);
        List<DischargeReportModel> dischargeReport = reportService.getReportDischarge(requestJson);
        List<UsageTransactionReport> transReport = reportService.getUsageTransactionReport(requestJson);
        AllUsageReportModel allReport = new AllUsageReportModel(usageSum, dischargeReport, transReport);
        allReport.setZipFileName(fileName);
        return allReport;
    }
    
    public String getFileNameZip(String reportNo, String memberid)
    {
        logger.info(">>>getFileNameZip<<<");

        String dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        String name = "RPT" + reportNo + "-" + memberid + "-" + dateStr;
        logger.info("Generated Zip file name is ");
        logger.info(name);

        return name;
    }
    
    public String formatDisplayDate(String d) {
        logger.info(">>>formatDisplayDate<<<");
        String date = null;
        String[] dateArray = splitDate(d);
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]) - 1;
        int year = Integer.parseInt(dateArray[2]);
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        Date mydate = calendar.getTime();
        SimpleDateFormat formatter
                = new SimpleDateFormat("yyyyMMdd", Locale.US);
        date = formatter.format(mydate);

        return date;
    }

    public String[] splitDate(String ddmmyyyy) {
        logger.info(">>>splitDate<<<");
        logger.info("ddmmyyyy::");
        logger.info(ddmmyyyy);
        String[] split;
        split = ddmmyyyy.split("/");

        return split;
    }
}
