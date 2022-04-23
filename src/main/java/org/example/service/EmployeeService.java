package org.example.service;

import org.example.dao.DaoFactory;
import org.example.dao.UserDao;
import org.example.entity.Customer;
import org.example.entity.Employee;

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
}