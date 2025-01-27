/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arg.ccra3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author kumpeep
 */

public class CountDataBlock {
   
private String countUsing;

    public String getCountUsing() {
        return countUsing;
    }

    public void setCountUsing(String countUsing) {
        this.countUsing = countUsing;
    }

    @Override
    public String toString() {
        return "CountDataBlock{" + "countUsing=" + countUsing + '}';
    }



    
    
}
