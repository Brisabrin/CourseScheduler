package com.example.myapplication;
import java.util.Calendar;
import java.util.UUID;

public class Assignments {
    public String id;
    public String classId;
    public String title;
    public Calendar dueDate;
    public String description;

    public Assignments(String title, Calendar dueDate, String description) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.dueDate = dueDate;
        this.description = description;
    }

    public Assignments() {
        this.id = "";
        this.title = "";
        this.dueDate = (Calendar) Calendar.getInstance().clone(); // Clone the Calendar instance
        this.dueDate.add(Calendar.DAY_OF_MONTH, 7); // Add 7 days to the cloned instance
        this.description = "";
    }

    public Assignments(Assignments other) {
        this.id = other.id;
        this.title = other.title;
        this.classId = other.classId;
        this.dueDate = other.dueDate;
        this.description = other.description;

    }



}
