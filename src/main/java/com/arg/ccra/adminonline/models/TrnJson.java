/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arg.ccra.adminonline.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "API_TRN_JSON")
@Data
@ToString
public class TrnJson implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRNAPIID")
    Long trnApiId;
    @Column(name = "AID")
    Long aId;
    @Column(name = "UID")
    Long uId;
    @Column(name = "MODULE")
    Long moduleNo;
    @Column(name = "ACCESS_TOKEN")
    String accessToken;
    @Column(name = "JSON_REQUEST")
    String jsonRequest;
    @Column(name = "JSON_RESPONSE")
    String jsonResponse;
    @Column(name = "REQUEST_TIME")
    Date requestTime;
    @Column(name = "RESPONSE_TIME")
    Date responseTime;
    @Column(name = "STATUS_CODE")
    Long statusCode;
    @Column(name = "ERROR_CODE")
    String errorCode;
    @Column(name = "GROUPID")
    Long groupId;
    @Column(name = "TOKENID")
    Long tokenId;

    
    
    
    
    

}
