package com.arg.ccra.adminonline.services;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.arg.ccra.adminonline.models.CtrlKey;

@Service
public class CtrlKeyService {
    
    private final JdbcTemplate jdbcTemplateAPI = new JdbcTemplate();
    
    public List<CtrlKey> getKey(){
        String sql = "SELECT TOP 1 * FROM API_CTRL_KEY " +
        "WHERE CTRLTYPE = 'CDIKEY' AND CTRL_FLAG = 1 " +
        "AND CTRL_START <= GETDATE() AND (CTRL_END IS NULL OR CTRL_END >= GETDATE() ) " +
        "ORDER BY CTRL_START";
         List<CtrlKey> results = jdbcTemplateAPI.query(sql,
            BeanPropertyRowMapper.newInstance(CtrlKey.class));
        return results;

    }
}
