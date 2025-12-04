package com.example.finalproject.models;

public class EventModel {
    private int id;
    private String eventName;
    private String eventDate;
    private String notes; // Tambahan baru

    public EventModel(int id, String eventName, String eventDate, String notes) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.notes = notes;
    }

    public int getId() { return id; }
    public String getEventName() { return eventName; }
    public String getEventDate() { return eventDate; }
    public String getNotes() { return notes; } // Ini yang dicari Adapter tadi
}