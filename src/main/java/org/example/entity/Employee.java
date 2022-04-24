package org.example.entity;

public class Employee extends User {

    public Employee(int id, String name, String password) {
    super(id, name, password);
}
    public Employee(String name, String password) {
        super(name, password);
    }
}
