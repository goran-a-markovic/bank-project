package org.example.entity;

public class Transaction {
    private int id;
    private int actFrom;
    private int actTo;
    private String tType;
    private double amount;

    //transaction for a regular transfer
    public Transaction(int id, int actFrom, int actTo, String tType, double amount) {
        this.id = id;
        this.actFrom = actFrom;
        this.actTo = actTo;
        this.tType = tType;
        this.amount = amount;
    }

    //transaction for depositing (pay attention to the order)
    public Transaction(int id, int actTo, String tType, double amount) {
        this.id = id;
        this.actTo = actTo;
        this.tType = tType;
        this.amount = amount;
    }

    //transaction for withdrawing
    public Transaction(int id, String tType, int actFrom, double amount) {
        this.id = id;
        this.actFrom = actFrom;
        this.tType = tType;
        this.amount = amount;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActFrom() {
        return actFrom;
    }

    public void setActFrom(int actFrom) {
        this.actFrom = actFrom;
    }

    public int getActTo() {
        return actTo;
    }

    public void setActTo(int actTo) {
        this.actTo = actTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTType() {
        return tType;
    }

    public void setTType(String tType) {
        this.tType = tType;
    }
}
