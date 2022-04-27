package org.example.entity;

import org.example.App;
import org.example.service.CustomerService;
import org.example.service.EmployeeService;

public class Employee extends User {

    public Employee(int id, String name, String password) {
    super(id, name, password);
}
    public Employee(String name, String password) {
        super(name, password);
    }

    public static void login(String name, String password) {
        User goal = EmployeeService.getEmployeeByName(name);
        if (goal != null) {
            if (goal.getPassword().equals(password)) {
                App.currentEmpId = goal.getId();
                System.out.println("Logged in successfully");
            } else {
                System.out.println("Your password is wrong, sir");
                App.flag = false;
                App.flagEmp = false;
            }
        } else {
            System.out.println("No employees with that name");
            App.flag = false;
            App.flagEmp = false;
        }
    }
}
