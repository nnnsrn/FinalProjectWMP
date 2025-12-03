package com.example.finalproject.models;

public class SplitBillModel {
    private int id;
    private double totalAmount;
    private int personCount;
    private double resultPerPerson;

    public SplitBillModel(int id, double totalAmount, int personCount, double resultPerPerson) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.personCount = personCount;
        this.resultPerPerson = resultPerPerson;
    }

    public int getId() { return id; }
    public double getTotalAmount() { return totalAmount; }
    public int getPersonCount() { return personCount; }
    public double getResultPerPerson() { return resultPerPerson; }
}
