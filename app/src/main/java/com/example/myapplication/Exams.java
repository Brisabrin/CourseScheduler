package com.example.myapplication;

import java.util.Calendar;
import java.util.UUID;

public class Exams {

    public String id;
    public String title;
    public Calendar datetime;
    public String location;

    public Exams(String title, Calendar datetime, String location) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.datetime = datetime;
        this.location = location;
    }
    public Exams() {
        this.id = "";
        this.title = "";
        this.datetime = (Calendar) Calendar.getInstance().clone();
        this.datetime.add(Calendar.DAY_OF_MONTH, 7);
        this.location = "";
    }
    public Exams(Exams other) {
        this.id = other.id;
        this.title = other.title;
        this.datetime = other.datetime;
        this.location = other.location;
    }

}
