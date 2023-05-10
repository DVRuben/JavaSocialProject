/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springbootrest.controllers;

import com.mycompany.springbootrest.dtos.PostDto;
import com.mycompany.springbootrest.exceptions.Message;
import com.mycompany.springbootrest.services.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/posts")
@Validated
public class PostController {
    
    @Autowired
    private PostService service;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody PostDto newPostDto){
        
        if(service.existsTitle(newPostDto.getTitle())){
            return new ResponseEntity(new Message("Title already exists"), HttpStatus.BAD_REQUEST);
        } else {
            service.create(newPostDto);
            return new ResponseEntity(new Message("Post successfully created"), HttpStatus.OK);
        }
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PostDto>> readAll(){
        return new ResponseEntity(service.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PostDto postDto) {
        if(!service.existsById(id)){
            return new ResponseEntity(new Message("ID not found"), HttpStatus.NOT_FOUND);
        }
        
        service.create(postDto);
        
        return new ResponseEntity(new Message("Post successfully updated"), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        if(!service.existsById(id)){
            return new ResponseEntity(new Message("ID not found"), HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity(new Message("Your request was successful"), HttpStatus.OK);
    }
    
}
