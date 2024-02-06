package com.example.myapplication;

import java.util.UUID;

public class TodoItem {
    public String id;
    public String title;
    public String description;
    public boolean isCompleted;

    public TodoItem(String title, String description, boolean isCompleted) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }
}
