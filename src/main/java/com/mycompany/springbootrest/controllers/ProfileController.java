/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springbootrest.controllers;

import com.mycompany.springbootrest.entities.ProfileEntity;
import com.mycompany.springbootrest.services.ProfileService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {
    
    @Autowired
    private ProfileService service;
    
    @GetMapping
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("id") Long userId) throws FileNotFoundException{
        File img = new File("C:\\Users\\Mauro\\Desktop\\DevSocial");
        
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(img));
        return ResponseEntity
                .status(200)
                .contentType(MediaType.IMAGE_JPEG)
                .body(inputStreamResource);
        
        /*JS
            var input = document.querySelector('input[type="file"]')

            var data = new FormData()
            data.append('file', input.files[0])
            data.append('user', 'hubot')

            fetch('/avatars', {
                method: 'POST',
                body: data
            })
         */
    }
    
    @PostMapping("/{id}/file")
    public void uploadFile(@PathVariable("id") Long userId, @RequestParam("profilePic") MultipartFile multipartFile) throws IOException{
        
        File dir = new File("C:\\Users\\Mauro\\Desktop\\DevSocial");
        if(!dir.exists()){
            dir.mkdirs();
        }
        
        String originalFilename = multipartFile.getOriginalFilename();
        long fileSize = multipartFile.getSize();
        String contentType = multipartFile.getContentType();
        
        File uploadedFile = new File(dir, originalFilename);
        
        multipartFile.transferTo(uploadedFile);
    }
    
    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProfileEntity> readByUsername(@PathVariable("username") String username){
        
        ProfileEntity foundEntity = service.readByUsername(username);
        return new ResponseEntity(foundEntity, HttpStatus.OK);
    }
}
