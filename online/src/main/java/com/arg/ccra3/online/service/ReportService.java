package com.arg.ccra3.online.service;

import ch.qos.logback.classic.Logger;
import com.arg.ccra3.model.report.DischargeReportModel;
import org.springframework.stereotype.Service;
import com.arg.ccra3.model.report.SummaryReportParam;
import com.arg.ccra3.model.report.UsageReportModel;
import com.arg.ccra3.model.report.UsageTransactionReport;
import java.text.SimpleDateFormat;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class ReportService {

    @Autowired
    private final JdbcTemplate jdbcTemplateAPI = new JdbcTemplate();

    private final Logger logger = (Logger) LoggerFactory.getLogger(ReportService.class);
    
    private final String DATE_FORMAT = "yyyy-MM-dd";

    private String loggerText = "";
    
    public List<DischargeReportModel> getReportDischarge(SummaryReportParam param) {
        String sql = "exec rp_usage_trn_discharge ?, ?, ?, ?, ? , ? , ? , ? , ? , ?, ?, ?";
        Object[] params = new Object[]{param.getuId(),param.getUserId(),new SimpleDateFormat(DATE_FORMAT).format(param.getReportDate()), new SimpleDateFormat(DATE_FORMAT).format(param.getStartDate()),new SimpleDateFormat(DATE_FORMAT).format(param.getEndDate()),param.getWhereCase(),param.getGroupAiId(),param.getGroupId(),param.getMonthlyPlan(),param.getSortPri(),param.getSortSec(),param.getSortMem()};
        List<DischargeReportModel> results = jdbcTemplateAPI.query(sql,
                BeanPropertyRowMapper.newInstance(DischargeReportModel.class),params);
        loggerText = String.format("DischargeReportModel:result: %s", results);
        logger.info(loggerText);
        return results;
    }
    
    public List<UsageReportModel> getUsageSummaryReport(SummaryReportParam param) {
        String sql = "exec rp_usage_summary ?, ?, ?, ?, ? , ? , ? , ? , ? , ?, ?, ?";
        Object[] params = new Object[]{param.getuId(),param.getUserId(),new SimpleDateFormat(DATE_FORMAT).format(param.getReportDate()),new SimpleDateFormat(DATE_FORMAT).format(param.getStartDate()),new SimpleDateFormat(DATE_FORMAT).format(param.getEndDate()),param.getWhereCase(),param.getGroupAiId(),param.getGroupId(),param.getMonthlyPlan(),param.getSortPri(),param.getSortSec(),param.getSortMem()};
        List<UsageReportModel> results = jdbcTemplateAPI.query(sql, BeanPropertyRowMapper.newInstance(UsageReportModel.class),params);
        loggerText = String.format("UsageReportModel:result: %s", results);
        logger.info(loggerText);
        return results;
    }
    
     public List<UsageTransactionReport> getUsageTransactionReport(SummaryReportParam param) {
        String sql = "exec rp_usage_trn  ?, ?, ?, ?, ?, ? , ? , ? , ? , ?, ?, ?";
        Object[] params = new Object[]{param.getuId(),param.getUserId(),new SimpleDateFormat(DATE_FORMAT).format(param.getReportDate()),new SimpleDateFormat(DATE_FORMAT).format(param.getStartDate()),new SimpleDateFormat(DATE_FORMAT).format(param.getEndDate()),param.getWhereCase(),param.getGroupAiId(),param.getGroupId(),param.getMonthlyPlan(),param.getSortPri(),param.getSortSec(),param.getSortMem()};
        List<UsageTransactionReport> results = jdbcTemplateAPI.query(sql, BeanPropertyRowMapper.newInstance(UsageTransactionReport.class),params);
        loggerText = String.format("UsageTransactionReport:result: %s", results);
        logger.info(loggerText);
        return results;
    }
}
