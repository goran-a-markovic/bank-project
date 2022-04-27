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
        if (goal != null) {
            if (goal.getPassword().equals(password)) {
                App.loggedIn = true;
                App.flagUser = true;
                App.currentUserId = goal.getId();
                System.out.println("Logged in successfully");
                System.out.println("The customer just logged in: " + goal.getId() + " " + goal.getName());
            } else {
                System.out.println("Your password is wrong, sir");
                App.flag = false;
                App.flagUser = false;
            }
        } else {
            System.out.println("No customers with that name");
            App.flag = false;
            App.flagUser = false;
        }
    }
}
