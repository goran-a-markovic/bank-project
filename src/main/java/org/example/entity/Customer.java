package org.example.entity;

import org.example.App;
import org.example.service.CustomerService;

public class Customer extends User {
    private static boolean loggedIn = false;
    private static int currentUserId = 0;

    public Customer(int id, String name, String password) {
        super(id, name, password);
    }
    public Customer(String name, String password) {
        super(name, password);
    }

    public static void login(String name, String password) {
        User goal = CustomerService.getCustomerByName(name);
        System.out.println(goal.getPassword());
        System.out.println(password);
        if (goal.getPassword().equals(password)) {
            App.loggedIn = true;
            App.currentUserId = goal.getId();
            System.out.println("Logged in successfully");
        }
        System.out.println("Logged in - " + App.loggedIn);
        System.out.println("Current ID - " + App.currentUserId);
    }
}
