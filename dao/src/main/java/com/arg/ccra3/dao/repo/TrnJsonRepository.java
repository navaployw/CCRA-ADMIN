/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arg.ccra3.dao.repo;

import com.arg.ccra3.model.TrnJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author navaployw
 */
@Repository
public interface TrnJsonRepository extends JpaRepository<TrnJson, Long> {
    
    
   
}
