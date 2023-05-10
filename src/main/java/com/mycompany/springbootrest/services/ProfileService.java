/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springbootrest.services;

import com.mycompany.springbootrest.entities.ProfileEntity;
import com.mycompany.springbootrest.repositories.ProfileRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileService {
    
    @Autowired
    ProfileRepository repository;
    
        public ProfileService(){
        
    }
    
    public List<ProfileEntity> findAll(){
        return repository.findAll();
    }
    
    public ProfileEntity readByUsername(String username){
        return repository.findByUsername(username);
    }
}
