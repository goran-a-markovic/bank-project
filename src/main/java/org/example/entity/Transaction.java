package org.example.entity;

public class Transaction {
    private int id;
    private int actFrom;
    private int actTo;
    private String tType;
    private double amount;
    private String status;

    public Transaction(int id, int actFrom, int actTo, String tType, double amount, String status) {
        this.id = id;
        this.actFrom = actFrom;
        this.actTo = actTo;
        this.tType = tType;
        this.amount = amount;
        this.status = status;
    }

    //transaction for a regular transfer
    public Transaction(int actFrom, int actTo, String tType, double amount, String status) {
        this.actFrom = actFrom;
        this.actTo = actTo;
        this.tType = tType;
        this.amount = amount;
        this.status = status;
    }

    //transaction for depositing (pay attention to the order)
    public Transaction(int actTo, String tType, double amount, String status) {
        this.actTo = actTo;
        this.tType = tType;
        this.amount = amount;
        this.actFrom = actTo;
        this.status = status;
    }

    //transaction for withdrawing
    public Transaction(String tType, int actFrom, double amount, String status) {
        this.actFrom = actFrom;
        this.tType = tType;
        this.amount = amount;
        this.actTo = actFrom;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", actFrom=" + actFrom +
                ", actTo=" + actTo +
                ", tType='" + tType + '\'' +
                ", amount=" + amount +
                '}';
    }


}
