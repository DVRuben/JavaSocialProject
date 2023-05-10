/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springbootrest.services;

import com.mycompany.springbootrest.dtos.UserDto;
import com.mycompany.springbootrest.entities.UserEntity;
import com.mycompany.springbootrest.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private List<UserEntity> userEntities;
    
    @Autowired
    UserRepository repository;
    
    public UserService(){
        
    }
    
    public List<UserEntity> findAll(){
        return repository.findAll();
    }
    
    
    public List<UserEntity> findAllPaginated(Integer page, Integer size){
        int offset = (page + 1) * size;
        if(offset > userEntities.size()){
            offset = userEntities.size();
        }
        return userEntities.subList(page * size, offset);
    }
    
    /*public void login(String email, String password){
        Iterator<UserEntity>
    }*/
    
    public UserDto create(UserDto newUserDto){
        
        String hashPassword = DigestUtils.sha256Hex(newUserDto.getPassword());
        
        UserEntity newUserEntity = new UserEntity(
                    newUserDto.getFirstname(), newUserDto.getLastname(), newUserDto.getEmail(), hashPassword, newUserDto.getUsername()
            );
        newUserEntity = repository.save(newUserEntity);
        return newUserDto;
    }
    
    public void update(UserDto updateDto){
        
        UserEntity oldEntity = repository.findById(updateDto.getId()).get();
        
        oldEntity.setFirstname(updateDto.getFirstname());
        oldEntity.setLastname(updateDto.getLastname());
        oldEntity.setEmail(updateDto.getEmail());
        
        repository.save(oldEntity);
    }
    
    public void deleteById(Long id){
        repository.deleteById(id);
    }
    
    public boolean existsById(Long id){
        return repository.existsById(id);
    }
    
    public UserEntity findById(Long id){
        return repository.findById(id).get();
    }
    
    public boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }
    
    public void addCopy(UserEntity userEntity){
        userEntities.add(userEntity);
    }
    
    /*public UserEntity getByUsername(String username) {
        userEntities = repository.findAll();
        UserEntity foundUser = null;
        
        for(UserEntity userEntity: userEntities){
            if(username == null ? userEntity.getUsername() == null : username.equals(userEntity.getUsername())){
                foundUser = userEntity;
            }        
        }
        return foundUser;
    }*/
}
