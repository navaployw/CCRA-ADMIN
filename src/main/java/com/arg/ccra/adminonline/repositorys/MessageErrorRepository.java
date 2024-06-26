/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arg.ccra.adminonline.repositorys;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arg.ccra.adminonline.models.MessageError;

@Repository
public interface MessageErrorRepository extends JpaRepository<MessageError, Long>{
    public List<MessageError> findByerrorCode(String errorCode);
}
