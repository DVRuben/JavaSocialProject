/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springbootrest.controllers;

import com.mycompany.springbootrest.dtos.UserDto;
import com.mycompany.springbootrest.entities.UserEntity;
import com.mycompany.springbootrest.exceptions.Message;
import com.mycompany.springbootrest.services.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@Validated
public class UserController {
    
    @Autowired
    private UserService service;
    
    @GetMapping("/find-users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDto>> readAll(
    @RequestParam(value="page", required=false) Integer page,
    @RequestParam(value="size", required=false) Integer size){
        
        if(page == null && size == null){
            return new ResponseEntity(service.findAll(), HttpStatus.OK);
        }
        if(size == null){
            size = 4;
        }
        return new ResponseEntity(service.findAllPaginated(page, size), HttpStatus.OK);
    }
    
    /*@PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) 
            return new ResponseEntity(new Mensaje("Campos erroneos"), HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }*/
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserEntity> readById(@PathVariable("id") Long id){
         if(!service.existsById(id)){
            return new ResponseEntity(new Message("ID not found"), HttpStatus.BAD_REQUEST);
        }
        
        UserEntity foundEntity = service.findById(id);
        return new ResponseEntity(foundEntity, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody UserDto newUserDto){
        
        if(service.existsByEmail(newUserDto.getEmail())){
            return new ResponseEntity(new Message("Email already exists"), HttpStatus.BAD_REQUEST);
        } else {
            service.create(newUserDto);
            return new ResponseEntity(new Message("User successfully created"), HttpStatus.OK);
        }
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        if(!service.existsById(id)){
            return new ResponseEntity(new Message("ID not found"), HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity(new Message("User successfully deleted"), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        if(!service.existsById(id)){
            return new ResponseEntity(new Message("ID not found"), HttpStatus.NOT_FOUND);
        }
        
        UserEntity user = service.findById(id);
        
        
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setUsername(userDto.getUsername());
        
        
        return new ResponseEntity(new Message("User successfully updated"), HttpStatus.OK);
    }
}
