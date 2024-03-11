
package com.arg.ccra.adminonline.models.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "SPM_USER")
@Data
public class UserSPM {

    @Id
    @Column(name = "UID", nullable = false, insertable = false, updatable = false)
    private Long uID;

    @Column(name = "GROUPID", nullable = false, insertable = false, updatable = false)
    private String groupID;

    @Column(name = "GROUPAIID", nullable = false, insertable = false, updatable = false)
    private String groupAIID;

    @Column(name = "GROUPCCRAID", nullable = false, insertable = false, updatable = false)
    private String groupCCRAID;

    @Column(name = "TITLE", nullable = false, insertable = false, updatable = false)
    private String title;

    @Column(name = "USER_NAME_LO", nullable = false, insertable = false, updatable = false)
    private String userNameLocal;

    @Column(name = "USER_NAME_EN", nullable = false, insertable = false, updatable = false)
    private String userNameEnglish;

    @Column(name = "MANAGERID", nullable = false, insertable = false, updatable = false)
    private String managerID;

    @Column(name = "USERID", nullable = false, insertable = false, updatable = false)
    private String userID;

    @Column(name = "PASSWORD", nullable = false, insertable = false, updatable = false)
    private String password;

    @Column(name = "ADMINFLAG", nullable = false, insertable = false, updatable = false)
    private String adminFlag;

    @Column(name = "DESCRIPTION", nullable = false, insertable = false, updatable = false)
    private String description;

    @Column(name = "PREFERREDLANGUAGE", nullable = false, insertable = false, updatable = false)
    private String preferredLanguage;

    @Column(name = "MAXCONNECTION", nullable = false, insertable = false, updatable = false)
    private String maxConnection;

    @Column(name = "LOGONFAIL", nullable = false, insertable = false, updatable = false)
    private String loginFail;

    @Column(name = "SESSIONTIMEOUT", nullable = false, insertable = false, updatable = false)
    private String sessionTimeout;

    @Column(name = "CHANGEPASSWORDNEXTLOGON", nullable = false, insertable = false, updatable = false)
    private String changePasswordNextLogin;

    @Column(name = "CANNOTCHANGEPASSWORD", nullable = false, insertable = false, updatable = false)
    private String cannotChangePassword;

    @Column(name = "PASSWORDNEVEREXPIRED", nullable = false, insertable = false, updatable = false)
    private String passwordNeverExpired;

    @Column(name = "PASSWORDEXPIREDPERIOD", nullable = false, insertable = false, updatable = false)
    private String passwordExpiredPeriod;

    @Column(name = "PASSWORDEXPIREDDATE", nullable = false, insertable = false, updatable = false)
    private String passwordExpiredDate;

    @Column(name = "EXPIREDATE", nullable = false, insertable = false, updatable = false)
    private String expiredDate;

    @Column(name = "LOGIN_PERMIT_MON", nullable = false, insertable = false, updatable = false)
    private String loginPermittedMon;

    @Column(name = "LOGIN_PERMIT_TUE", nullable = false, insertable = false, updatable = false)
    private String loginPermittedTue;

    @Column(name = "LOGIN_PERMIT_WED", nullable = false, insertable = false, updatable = false)
    private String loginPermittedWed;

    @Column(name = "LOGIN_PERMIT_THU", nullable = false, insertable = false, updatable = false)
    private String loginPermittedThu;

    @Column(name = "LOGIN_PERMIT_FRI", nullable = false, insertable = false, updatable = false)
    private String loginPermittedFri;

    @Column(name = "LOGIN_PERMIT_SAT", nullable = false, insertable = false, updatable = false)
    private String loginPermittedSat;

    @Column(name = "LOGIN_PERMIT_SUN", nullable = false, insertable = false, updatable = false)
    private String loginPermittedSun;

    @Column(name = "DELETED", nullable = false, insertable = false, updatable = false)
    private String deleted;

    @Column(name = "DISABLED", nullable = false, insertable = false, updatable = false)
    private String disabled;

    @Column(name = "DISABLEDREASON", nullable = false, insertable = false, updatable = false)
    private String disabledReason;

    @Column(name = "SHAREMAILBOX", nullable = false, insertable = false, updatable = false)
    private String shareMailBox;

    @Column(name = "EMAIL", nullable = false, insertable = false, updatable = false)
    private String email;

    @Column(name = "CREATEDBY", nullable = false, insertable = false, updatable = false)
    private String createdBy;

    @Column(name = "CREATEDDATE", nullable = false, insertable = false, updatable = false)
    private String createdDate;

    @Column(name = "UPDATEDBY", nullable = false, insertable = false, updatable = false)
    private String updatedBy;

    @Column(name = "UPDATEDDATE", nullable = false, insertable = false, updatable = false)
    private String updatedDate;

   
}
