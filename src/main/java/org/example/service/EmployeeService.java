package org.example.service;

import org.example.App;
import org.example.dao.DaoFactory;
import org.example.dao.UserDao;
import org.example.entity.Customer;
import org.example.entity.Employee;
import org.example.entity.User;

import java.util.List;
import java.util.Scanner;

public class EmployeeService {

    public static void insertEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the employee");
        String name = scanner.nextLine();
        System.out.println("Please enter the password");
        String password = scanner.nextLine();
        Employee employee = new Employee(name, password);

        UserDao employeeDao = DaoFactory.getEmployeeDao();
        System.out.println(employee.getClass());
        employeeDao.insert(employee);
    }

    public static void getEmployeeById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the id of the employee");
        int id = scanner.nextInt();
        UserDao employeeDao = DaoFactory.getEmployeeDao();
        User employee = employeeDao.getUserById(id);
        System.out.println("Here is the employee you wanted: " + employee.toString());
    }

    public static User getEmployeeByName(String name) {
        UserDao employeeDao = DaoFactory.getEmployeeDao();
        User employee = employeeDao.getUserByName(name);
        return employee;
    }


    public static void getAllEmployees() {
        System.out.println("You don't really need this method :D ");
    }



}