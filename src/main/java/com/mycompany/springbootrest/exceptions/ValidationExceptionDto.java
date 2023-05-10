/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springbootrest.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ValidationExceptionDto implements Serializable{
    
    private List<String> messages;
    
    public ValidationExceptionDto(){
        messages = new ArrayList<>();
    }
    
    public void addMessage(String message){
        messages.add(message);
    }
    
    public List<String> getMessage(){
        return messages;
    }
}
