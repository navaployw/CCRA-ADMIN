
package com.arg.ccra.adminonline.models.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "SPM_SESSION")
@Data
public class Session {
    
    @Id
    @Column(name = "SESSIONID", nullable = false, updatable = false, insertable = false)
    private Long sessionID;
    
    @Column(name = "UID", nullable = false, updatable = false, insertable = false)
    private Long uID;
    
    @Column(name = "LOGINDATE", nullable = false, updatable = false, insertable = false)
    private Long loginDate;
    
    @Column(name = "LOGOUTDATE", nullable = false, updatable = false, insertable = false)
    private Long logoutDate;
    
    @Column(name = "IPADDRESS", nullable = false, updatable = false, insertable = false)
    private Long ipAddress;
    
    @Column(name = "CHANNELID", nullable = false, updatable = false, insertable = false)
    private Long channelID;
    
    @Column(name = "lastActiveTime", nullable = false, updatable = false, insertable = false)
    private Long LACTIVETIME;
    
    @Column(name = "LOGINATTEMPT", nullable = false, updatable = false, insertable = false)
    private Long loginAttempt;
    
    @Column(name = "LOGINFLAG", nullable = false, updatable = false, insertable = false)
    private Long loginFlag;

    
}
