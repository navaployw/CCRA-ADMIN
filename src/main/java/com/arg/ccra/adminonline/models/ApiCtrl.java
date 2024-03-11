/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

@Entity
@Table(name = "API_CTRL")
@Data
public class ApiCtrl implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CTRLID")
    private Long ctrlId;
    @Column(name = "CTRLTYPE")
    private String ctrlType;
    @Column(name = "CTRLVALUE")
    private String ctrlValue;
    @Column(name = "CTRLDESCRIPTION")
    private String ctrlDescription;
    @Column(name = "CTRL_START")
    private Date ctrlStart;
    @Column(name = "CTRL_END")
    private Date ctrlEnd;
    @Column(name = "CTRL_FLAG")
    private Boolean ctrlFlag;

}
