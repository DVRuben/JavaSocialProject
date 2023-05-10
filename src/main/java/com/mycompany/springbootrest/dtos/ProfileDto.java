/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springbootrest.dtos;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;


public class ProfileDto implements Serializable{
    
    private Long id;
    private String profileImg;
    @NotBlank(message = "Username cannot be blank")
    private String username;
    private String description;

    public ProfileDto() {
    }

    public ProfileDto(Long id, String profileImg, String username, String description) {
        this.id = id;
        this.profileImg = profileImg;
        this.username = username;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProfileDto{" + "id=" + id + ", profileImg=" + profileImg + ", username=" + username + ", description=" + description + '}';
    }
    
    
}
