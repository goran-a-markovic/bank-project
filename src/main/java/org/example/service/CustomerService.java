package org.example.service;

import org.example.dao.CustomerDaoImpl;
import org.example.dao.DaoFactory;
import org.example.dao.UserDao;
import org.example.entity.Customer;
import org.example.entity.User;

import java.util.List;
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
    public static void getCustomerById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the id of the customer");
        int id = scanner.nextInt();
        UserDao customerDao = DaoFactory.getCustomerDao();
        User customer = customerDao.getUserById(id);
        System.out.println("Here is the customer you wanted: " + customer.toString());
    }

    public static void getAllCustomers() {
        System.out.println("All customers:");
        UserDao customerDao = DaoFactory.getCustomerDao();
        List<User> customers = customerDao.getAllUsers();
        for(User customer : customers) {
            System.out.println(customer);
        }
    }
//
    public static void updateCustomer() {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        System.out.println("What is the ID of the customer you would like to update?");
        int id = scannerInt.nextInt();
        System.out.println("Please enter the changed name");
        String name = scanner.nextLine();
        System.out.println("Please enter the new password");
        String password = scanner.nextLine();

        Customer customer = new Customer(id, name, password);
        UserDao customerDao = DaoFactory.getCustomerDao();
        customerDao.update(customer);
    }
//
    public static void deleteCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the ID of the customer you would like to delete?");
        int id = scanner.nextInt();
        UserDao customerDao = DaoFactory.getCustomerDao();
        customerDao.delete(id);
    }
}
