package com.parrotdevs.wellness.model;

import java.util.ArrayList;

public class UserPath {

    private String userId;


    public UserPath(){


    }
    public UserPath(String userId) {

        this.userId = userId;

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
