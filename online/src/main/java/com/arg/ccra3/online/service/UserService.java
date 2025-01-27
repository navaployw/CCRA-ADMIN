package com.arg.ccra3.online.service;

import ch.qos.logback.classic.Logger;
import com.arg.ccra3.dao.UserDAO;
import com.arg.ccra3.model.User;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.arg.ccra3.online.util.ResponseEntityFactory;
import com.arg.ccra3.dao.repo.UserAPIRepo;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class UserService {

    @Autowired
    private UserAPIRepo userApiRepo;
    private final Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);
    @Autowired
    private JdbcTemplate jdbcTemplateAPI;

    public User getUserFromUserID(String userID) {
        UserDAO userDao = new UserDAO(this.jdbcTemplateAPI, userApiRepo);
        return userDao.getUserFromUserID(userID);
    }

    public List<Object> getAPIUserList() {
        UserDAO userDao = new UserDAO(this.jdbcTemplateAPI, userApiRepo);
        return userDao.getAPIUserList();
    }

    public Object getUserDetailByAID(long aID) {
        UserDAO userDao = new UserDAO(this.jdbcTemplateAPI, userApiRepo);
        return userDao.getUserDetailByAID(aID);
    }

    public List<Object> getListDataBlock() {
        UserDAO userDao = new UserDAO(this.jdbcTemplateAPI, userApiRepo);
        return userDao.getListDataBlock();
    }

    public List<Long> getListDataBlockByUID(Long uID) {
        UserDAO userDao = new UserDAO(this.jdbcTemplateAPI, userApiRepo);
        return userDao.getListDataBlockByUID(uID);
    }

    public List<Map<String, Object>> searchWithUserId(String userId) {
        UserDAO userDao = new UserDAO(this.jdbcTemplateAPI, userApiRepo);
        return userDao.searchWithUserId(userId);
    }

    public ResponseEntity<Object> checkUserDuplicateAI(long uid, long gid, long gaiid) {
        try {
            UserDAO userDao = new UserDAO(this.jdbcTemplateAPI, userApiRepo);
            return ResponseEntityFactory.ok(
                    userDao.checkUserDuplicateAI(uid, gid, gaiid)
            );
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntityFactory.internalServerError(e.getMessage());
        }
    }

    public ResponseEntity<Object> deleteUserByAIDandUID(String by, long aID, long uID) {
        try {
            UserDAO userDao = new UserDAO(this.jdbcTemplateAPI, userApiRepo);
            userDao.deleteUserByAIDandUID(by, aID, uID);
            return ResponseEntityFactory.ok("sucess");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntityFactory.internalServerError(e.getMessage());
        }
    }

    public ResponseEntity<Object> saveUserInfo(Map<String, Object> user) {
        try {
            UserDAO userDao = new UserDAO(this.jdbcTemplateAPI, userApiRepo);
            userDao.setUserAPIRepo(userApiRepo);
            return ResponseEntityFactory.ok(userDao.saveUserInfo(new JSONObject(user)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntityFactory.internalServerError(e.getMessage());
        }
    }
    
    public boolean isTokenActiveByAID(long aID) {
        UserDAO userDao = new UserDAO(this.jdbcTemplateAPI, userApiRepo);
        return userDao.isTokenActiveByAID(aID);
    }
}
