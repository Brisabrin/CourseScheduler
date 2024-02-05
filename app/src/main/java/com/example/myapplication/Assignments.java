package com.example.myapplication;
import java.util.Calendar;
import java.util.UUID;

public class Assignments {
    public String id;
    public String classId;
    public String title;
    public Calendar dueDate;  // Change the type to Calendar
    public String description;

    public Assignments(String title, Calendar dueDate, String description) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.dueDate = dueDate;
        this.description = description;
    }
}
