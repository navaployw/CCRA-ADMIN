/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arg.ccra.adminonline.services;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arg.ccra.adminonline.models.MessageError;
import com.arg.ccra.adminonline.repositorys.MessageErrorRepository;

@Service
public class MessageErrorService {
    @Autowired
    private MessageErrorRepository messageRepo;
    
    public List<MessageError> getMessageByCode(String errorCode){

        return messageRepo.findByerrorCode(errorCode);
    }
}
