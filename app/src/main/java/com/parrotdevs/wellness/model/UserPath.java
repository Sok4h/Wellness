package com.parrotdevs.wellness.model;

import java.util.ArrayList;

public class UserPath {

    private String id,userId;
    private ArrayList<Exercise> exerciseArrayList;

    public UserPath(){


    }
    public UserPath(String id, String userId) {
        this.id = id;
        this.userId = userId;
        exerciseArrayList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Exercise> getExerciseArrayList() {
        return exerciseArrayList;
    }

    public void setExerciseArrayList(ArrayList<Exercise> exerciseArrayList) {
        this.exerciseArrayList = exerciseArrayList;
    }
}
