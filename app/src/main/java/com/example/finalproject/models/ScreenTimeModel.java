package com.example.finalproject.models;

public class ScreenTimeModel {
    private int id;
    private String date;
    private int duration;

    public ScreenTimeModel(int id, String date, int duration) {
        this.id = id;
        this.date = date;
        this.duration = duration;
    }

    public int getId() { return id; }
    public String getDate() { return date; }
    public int getDuration() { return duration; }
}
