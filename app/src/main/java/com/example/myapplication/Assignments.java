package com.example.myapplication;

public class Assignments {
    public String id;
    public String classId;
    public String title;
    public String datedue;
    public String description;

    public Assignments(String id, String title, String datedue, String description) {
        this.id = id;
        this.title = title;
        this.datedue = datedue;
        this.description = description;
    }
}
