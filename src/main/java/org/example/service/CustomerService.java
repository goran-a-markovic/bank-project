package org.example.service;

import org.example.dao.CustomerDaoImpl;
import org.example.dao.DaoFactory;
import org.example.dao.UserDao;
import org.example.entity.Customer;

import java.util.Scanner;

public class CustomerService {

    public static void insertCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the user");
        String name = scanner.nextLine();
        System.out.println("Please enter the password");
        String password = scanner.nextLine();
        Customer customer = new Customer(name, password);

        UserDao customerDao = DaoFactory.getCustomerDao();
        System.out.println(customer.getClass());
        customerDao.insert(customer);
    }
}
