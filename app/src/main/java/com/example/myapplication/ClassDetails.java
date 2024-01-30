package com.example.myapplication;

import java.util.UUID;

public class ClassDetails {
    public String id;
    public String title;
    public String datetime;
    public String instructor;
    public ClassDetails(String title, String datetime, String instructor) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.datetime = datetime;
        this.instructor = instructor;

    }
}
