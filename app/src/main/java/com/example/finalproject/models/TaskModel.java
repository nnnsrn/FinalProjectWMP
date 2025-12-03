package com.example.finalproject.models;

public class TaskModel {
    private int id;
    private String title;
    private String description;
    private int isDone;

    public TaskModel(int id, String title, String description, int isDone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getIsDone() { return isDone; }
}
