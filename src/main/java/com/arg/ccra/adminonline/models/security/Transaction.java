package com.arg.ccra.adminonline.models.security;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "SPM_TRANSACTION")
@Data
public class Transaction{
    @Id
    @Column(name = "TRANSACTIONID", nullable = false, updatable = false, insertable = false)
    private Long transactionID = null;
    @Transient
    private Date createdDate = null;
    @Transient
    private Date updatedDate = null;

    @Column(name = "CREATEDBY", nullable = false)
    private Integer createdBy = null;

    @Column(name = "DATAID", nullable = false)
    private Integer dataID = null;

    @Column(name = "GROUPAIID", nullable = false)
    private Integer groupAIID = null;

    @Column(name = "GROUPID", nullable = false)
    private Integer groupID = null;

    @Column(name = "OBJECTID", nullable = false)
    private Integer objectID = null;

    @Column(name = "SESSIONID", nullable = false)
    private Integer sessionID = null;

    @Column(name = "UID", nullable = false)
    private Integer uID = null;

    @Column(name = "UPDATEDBY", nullable = false)
    private Integer updatedBy = null;
    @Transient
    private Long expenseID = null;

    @Column(name = "CHANNEL", nullable = false)
    private String channel = null;

    @Column(name = "AMOUNTRESULT", nullable = false)
    private int amountResult = 0;

    
    public void setupDate(){
        Date now = new Date(System.currentTimeMillis());
        createdDate = now;
        updatedDate = now;
    }

}
