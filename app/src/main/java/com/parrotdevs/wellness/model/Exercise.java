package com.parrotdevs.wellness.model;

public class Exercise {

    String id,categoryId,categoryName,audioLink,day,description,length,categoryType,backgroundImg;

    public Exercise() {

    }


    public Exercise(String id, String categoryId,String categoryName,String categoryType, String audioLink, String day, String description, String length,String backgroundImg) {
        this.id = id;
        this.categoryId = categoryId;
        this.audioLink = audioLink;
        this.categoryType = categoryType;
        this.categoryName = categoryName;
        this.day = day;
        this.description = description;
        this.length = length;
        this.backgroundImg = backgroundImg;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }
}
