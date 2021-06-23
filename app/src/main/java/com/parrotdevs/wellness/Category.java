package com.parrotdevs.wellness;

import java.util.ArrayList;

public class Category {

    String id,name,cardDescription,img,days,color,description,type,backgroundImg;


    public Category(){

    }

    public Category(String id, String name, String cardDescription, String urlImage,String type,String backgroundImg) {
        this.id = id;
        this.name = name;
        this.cardDescription = cardDescription;
        this.img = urlImage;
        this.days = days;
        this.type=type;
        this.backgroundImg = backgroundImg;


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

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }
}
