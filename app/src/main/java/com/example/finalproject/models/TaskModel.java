package com.example.finalproject.models;

public class TaskModel {
    private String id; // Firebase pakai String, bukan int
    private String title;
    private String description;
    private int isDone;

    // WAJIB ADA: Konstruktor kosong untuk Firebase
    public TaskModel() {}

    public TaskModel(String id, String title, String description, int isDone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }

    // Getter dan Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getIsDone() { return isDone; }
}