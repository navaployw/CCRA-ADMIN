/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arg.ccra.adminonline.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "API_MESSAGE_ERR")
@Data
public class MessageError {
    @Id
    @Column(name = "STATUS_CODE")
    Integer statusCode;
    @Column(name ="ERROR_CODE")
    String errorCode;
    @Column(name ="ERROR_MESSAGE")
    String errorMessage;
    @Transient
    Integer transactionId;
    @Transient
    Integer tokenId;

    
    
    
}
