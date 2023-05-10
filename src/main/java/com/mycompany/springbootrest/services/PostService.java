/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springbootrest.services;

import com.mycompany.springbootrest.dtos.PostDto;
import com.mycompany.springbootrest.entities.PostEntity;
import com.mycompany.springbootrest.repositories.PostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    
    @Autowired
    private PostRepository repository;
    
    public PostDto create(PostDto newPostDto){
        
        PostEntity newPostEntity = new PostEntity(
                 newPostDto.getTitle(), newPostDto.getContent()
            );
        newPostEntity = repository.save(newPostEntity);
        return newPostDto;
    }
    
    public boolean existsTitle(String title){
        if(repository.existsByTitle(title)){
            return true;
        }else{
            return false;
        }
    }
    
    public List<PostEntity> findAll(){
        return repository.findAll();
    }
    
        
    public boolean existsById(Long id){
        return repository.existsById(id);
    }
    
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
