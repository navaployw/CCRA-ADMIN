package com.arg.ccra.adminonline.models.security;

import java.io.Serializable;
import java.time.Instant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "VIEW_API_USER")
@Data
public class ViewApiUser implements Serializable {
    @Id
    @Column(name = "AID")
    private Integer aID;
    
    @Column(name = "UID")
    private Integer uID;
    
    @Column(name = "USERID")
    private String userID;
    
    @Column(name = "GROUPID")
    private String groupID;
    
    @Column(name = "GROUPAIID")
    private String groupAIID;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "AICODE")
    private String aiCode;
    
    @Column(name = "SECRETKEY")
    private String secretKey;
    
    @Column(name = "SECRET_START")
    private Instant secretStart;
    
    @Column(name = "SECRET_END")
    private Instant secretEnd;
    
    @Column(name = "FLAG_ACTIVE")
    private Boolean flagActive;

    


    
    
}
