package com.example.finalproject.models;

public class ScreenTimeModel {
    private int id;
    private String date;
    private int duration; // Dalam detik

    public ScreenTimeModel(int id, String date, int duration) {
        this.id = id;
        this.date = date;
        this.duration = duration;
    }

    public int getId() { return id; }
    public String getDate() { return date; }
    public int getDuration() { return duration; }

    // Ini yang dicari Adapter! Mengubah detik ke menit
    public int getMinutesUsed() {
        return duration / 60;
    }
}