package com.arg.ccra.adminonline.models.api;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "API_User")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class UserAPI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AID", insertable = false, updatable = false)
    private Long aid;
    @Column(name = "UID", nullable = false)
    private Long uid;
    @Column(name = "GROUPID")
    private Long groupid;
    @Column(name = "GROUPAIID")
    private Long groupaiid;
    @Column(name = "USERID")
    private String userid;
    @Column(name = "SECRETKEY")
    private String secretkey;
    @Column(name = "SECRET_START")
    private Date secret_start;
    @Column(name = "SECRET_END")
    private Date secret_end;
    @Column(name = "THRESHOLD_WEEK")
    private Integer threshold_week;
    @Column(name = "DELETED")
    private Boolean deleted;
    @Column(name = "DELETEDBY")
    private Long deletedby;
    @Column(name = "DELETEDDATE")
    private Date deleteddate;

    @Column(name = "CREATEDBY")
    private Long createdby;   

  
    @Column(name = "CREATEDDATE")
    private Date createddate;
    @Column(name = "UPDATEBY")
    private Long updateby;
    @Column(name = "UPDATEDATE")
    private Date updatedate;
    @Column(name = "DISABLED")
    private Boolean disabled;
    @Column(name = "THRESHOLD_WEEK_COUNT")
    private Integer threshold_week_count;

    
}
