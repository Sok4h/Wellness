package com.parrotdevs.wellness.model;

public class Exercise {

    String id,categoryId,audioLink,day,description, length;

    public Exercise() {

    }


    public Exercise(String id, String categoryId, String audioLink, String day, String description, String length) {
        this.id = id;
        this.categoryId = categoryId;
        this.audioLink = audioLink;
        this.day = day;
        this.description = description;
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
