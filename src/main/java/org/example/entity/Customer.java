package org.example.entity;

public class Customer extends User {

    public Customer(int id, String name, String password) {
        super(id, name, password);
    }
    public Customer(String name, String password) {
        super(name, password);
    }
}
