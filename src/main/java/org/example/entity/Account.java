package org.example.entity;

public class Account {
    private int actNumber;
    private String actType;
    private double balance;
    private String status;
    private int userId;

    public Account(int actNumber, String actType, double balance, String status, int userId) {
        this.actNumber = actNumber;
        this.actType = actType;
        this.balance = balance;
        this.status = status;
        this.userId = userId;
    }

    public Account(String actType, double balance, String status, int userId) {
        this.actType = actType;
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

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
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

    @Override
    public String toString() {
        return "Account{" +
                "actNumber=" + actNumber +
                ", actType='" + actType + '\'' +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }
}
