/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springbootrest.dtos;

import com.mycompany.springbootrest.entities.UserEntity;
import java.io.Serializable;
import java.time.LocalDate;


public class PostDto implements Serializable{
    
    private Long id;
    private UserEntity author;
    private String title;
    private String content;
    private LocalDate date;

    public PostDto() {
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PostDto{" + "id=" + id + ", author=" + author + ", title=" + title + ", content=" + content + ", date=" + date + '}';
    }
    
    
}
