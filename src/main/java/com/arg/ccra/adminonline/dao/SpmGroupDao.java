/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arg.ccra.adminonline.dao;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.arg.ccra.adminonline.models.Member;

public class SpmGroupDao {
    
    private JdbcTemplate jdbcTemplateAPI;
    private final Logger logger = (Logger) LoggerFactory.getLogger(BeforeGetReportDAO.class);

    public SpmGroupDao() {
    }

    public SpmGroupDao(JdbcTemplate jdbcTemplateAPI) {
        this.jdbcTemplateAPI = jdbcTemplateAPI;
    }
    
    public List<Member> getMember(){
        final String sql = "select distinct groupid, group_name_en from spm_group (nolock) where aiflag=1 and deleted ='0' order by group_name_en";
        List<Member> results = this.jdbcTemplateAPI.query(sql,BeanPropertyRowMapper.newInstance(Member.class));
        String infoLog = String.format("getMember>>>>> %s", results);
        logger.info(infoLog);
        return results;
        
    }
}
