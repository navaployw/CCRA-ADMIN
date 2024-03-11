package com.arg.ccra.adminonline.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arg.ccra.adminonline.models.api.UserAPI;

public interface UserAPIRepo extends JpaRepository<UserAPI, Long> {
    public UserAPI findByUid(Long uid);
}