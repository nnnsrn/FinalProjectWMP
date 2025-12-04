package com.example.finalproject.models;

public class SplitBillModel {
    private int id;
    private String description;
    private String date;
    private double totalAmount;
    private int personCount;
    private double perPersonAmount;

    public SplitBillModel(int id, String description, String date, double totalAmount, int personCount, double perPersonAmount) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.totalAmount = totalAmount;
        this.personCount = personCount;
        this.perPersonAmount = perPersonAmount;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public double getTotalAmount() { return totalAmount; } // Dipakai Adapter
    public int getNumberOfPeople() { return personCount; } // Dipakai Adapter
    public double getPerPersonAmount() { return perPersonAmount; } // Dipakai Adapter
}