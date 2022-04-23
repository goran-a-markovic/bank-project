package org.example.entity;

public class Account {
    private int actNumber;
    private String type;
    private double balance;
    private String status;
    private int userId;

    public Account(int actNumber, String type, double balance, String status, int userId) {
        this.actNumber = actNumber;
        this.type = type;
        this.balance = balance;
        this.status = status;
        this.userId = userId;
    }

    public Account(String type, double balance, String status, int userId) {
        this.type = type;
        this.balance = balance;
        this.status = status;
        this.userId = userId;
    }

    public int getActNumber() {
        return actNumber;
    }

    public void setActNumber(int actNumber) {
        this.actNumber = actNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
