package com.parrotdevs.wellness.model;

public class User {
    private String name,id,email,description,userPhoto;

    public User() {

    }

    public User(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.description = "";
        this.userPhoto = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
}
