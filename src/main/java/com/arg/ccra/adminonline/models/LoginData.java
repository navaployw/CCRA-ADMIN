/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arg.ccra.adminonline.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginData {
    
    private Boolean result;
    private String message;
    private Integer userId;
    private Integer groupId;
    private Integer groupAIID;
    private String token;

}



  

 

   
    
    
    

