package com.example.finalproject.models;

public class EventModel {
    private int id;
    private String eventName;
    private String eventDate;

    public EventModel(int id, String eventName, String eventDate) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public int getId() { return id; }
    public String getEventName() { return eventName; }
    public String getEventDate() { return eventDate; }
}
