package com.example.myapplication;

import java.util.UUID;

public class Assignments {
    public String id;
    public String classId;
    public String title;
    public String datedue;
    public String description;

    public Assignments(String title, String datedue, String description) {
        this.id = UUID.randomUUID().toString();;
        this.title = title;
        this.datedue = datedue;
        this.description = description;
    }
}
