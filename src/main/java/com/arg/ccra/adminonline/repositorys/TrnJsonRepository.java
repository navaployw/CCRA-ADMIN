/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arg.ccra.adminonline.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arg.ccra.adminonline.models.TrnJson;

@Repository
public interface TrnJsonRepository extends JpaRepository<TrnJson, Long> {
    
    
   
}
