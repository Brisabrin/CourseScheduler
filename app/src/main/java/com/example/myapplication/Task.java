package com.example.myapplication;

import java.util.UUID;

public class Task {
    private String title;
    private String deadline;
    private String description;
    private boolean isCompleted;

    public Task(String title, String deadline, String description, boolean isCompleted) {
        this.title = title;
        this.deadline = deadline;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
