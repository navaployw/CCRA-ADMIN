package com.arg.ccra3.online.service;

import com.arg.ccra3.model.security.ViewApiUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ViewApiUserService {
    
//@Autowired
//    private ViewApiUserRepositories viewApiUserRepository;
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
//       return viewApiUserRepository.findByaiCode(aiCode);
    }
    
    public List<ViewApiUser> getUserByAICodeAndUserId(String aiCode,String userId) throws Exception {
        String sql = "SELECT * FROM VIEW_API_USER WHERE AICODE = ? and USERID = ?";
        Object[] params = new Object[]{aiCode, userId};
        List<ViewApiUser> results = jdbcTemplateAPI.query(sql,
            BeanPropertyRowMapper.newInstance(ViewApiUser.class),params);
        return results; 
        //       return viewApiUserRepository.findByaiCodeAndUserID(aiCode,userId);
    }
    
    public List<ViewApiUser> getUserByAICodePasswordUserId(String aiCode,String password,String userId) throws Exception {
        String sql = "SELECT * FROM VIEW_API_USER WHERE AICODE = ? and USERID = ? and PASSWORD = ?";
        Object[] params = new Object[]{aiCode, userId, password};
         List<ViewApiUser> results = jdbcTemplateAPI.query(sql,
            BeanPropertyRowMapper.newInstance(ViewApiUser.class),params);
        return results; 
//       return viewApiUserRepository.findByaiCodeAndPasswordAndUserID(aiCode,password,userId);
    }       
    
    public List<ViewApiUser> getAllUser() throws Exception {
        String sql = "SELECT * FROM VIEW_API_USER WHERE FLAG_ACTIVE = true";
        List<ViewApiUser> results = jdbcTemplateAPI.query(sql,
            BeanPropertyRowMapper.newInstance(ViewApiUser.class));
        return results; 
//       return viewApiUserRepository.findByflagActive(true);
    }      
}
