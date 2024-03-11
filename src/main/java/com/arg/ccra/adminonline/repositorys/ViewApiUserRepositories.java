/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arg.ccra.adminonline.repositorys;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arg.ccra.adminonline.models.security.ViewApiUser;

@Repository
public interface ViewApiUserRepositories extends JpaRepository<ViewApiUser, Long> {
        public List<ViewApiUser> findByaiCode(String aiCode);
        
        public List<ViewApiUser> findByaiCodeAndUserID(String aiCode,String userID);
        
        public List<ViewApiUser> findByaiCodeAndPasswordAndUserID(String aiCode,String password,String userID);
        
        public List<ViewApiUser> findByflagActive(Boolean active);
}
