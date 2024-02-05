package com.example.myapplication;

import java.util.Calendar;

public class Exams {

    public String title;
    public Calendar datetime;  // Change the type to Calendar
    public String location;

    public Exams(String title, Calendar datetime, String location) {
        this.title = title;
        this.datetime = datetime;
        this.location = location;
    }
}
