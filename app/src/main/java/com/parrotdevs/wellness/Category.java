package com.parrotdevs.wellness;

public class Category {

    String id,name,description,urlImage,days;

    public Category(){

    }

    public Category(String id, String name, String description, String urlImage, String days) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.urlImage = urlImage;
        this.days = days;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
