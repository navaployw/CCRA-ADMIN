package com.arg.ccra.adminonline.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.arg.ccra.adminonline.models.security.ViewApiUser;

@Service
public class ViewApiUserService {
    @Autowired
    private JdbcTemplate jdbcTemplateAPI;

    public ViewApiUserService() {
    }

    public ViewApiUserService(JdbcTemplate jdbcTemplateAPI) {
        this.jdbcTemplateAPI = jdbcTemplateAPI;
    }
    
    public List<ViewApiUser> getUserByAICode(String aiCode) throws Exception {
        String sql = "SELECT * FROM VIEW_API_USER WHERE AICODE = ?";
        List<ViewApiUser> results = jdbcTemplateAPI.query(sql,
            BeanPropertyRowMapper.newInstance(ViewApiUser.class),aiCode);
        return results;
    }
    
    public List<ViewApiUser> getUserByAICodeAndUserId(String aiCode,String userId) throws Exception {
        String sql = "SELECT * FROM VIEW_API_USER WHERE AICODE = ? and USERID = ?";
        Object[] params = new Object[]{aiCode, userId};
        List<ViewApiUser> results = jdbcTemplateAPI.query(sql,
            BeanPropertyRowMapper.newInstance(ViewApiUser.class),params);
        return results;
    }
    
    public List<ViewApiUser> getUserByAICodePasswordUserId(String aiCode,String password,String userId) throws Exception {
        String sql = "SELECT * FROM VIEW_API_USER WHERE AICODE = ? and USERID = ? and PASSWORD = ?";
        Object[] params = new Object[]{aiCode, userId, password};
         List<ViewApiUser> results = jdbcTemplateAPI.query(sql,
            BeanPropertyRowMapper.newInstance(ViewApiUser.class),params);
        return results;
    }       
    
    public List<ViewApiUser> getAllUser() throws Exception {
        String sql = "SELECT * FROM VIEW_API_USER WHERE FLAG_ACTIVE = true";
        List<ViewApiUser> results = jdbcTemplateAPI.query(sql,
            BeanPropertyRowMapper.newInstance(ViewApiUser.class));
        return results;
    }      
}
