package org.example.service;

import org.example.App;
import org.example.dao.*;
import org.example.entity.Account;
import org.example.entity.Customer;
import org.example.entity.Transaction;
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
        customerDao.insert(customer);
    }

    //    public static void insertCustomer(Customer customer) {
//        UserDao customerDao = DaoFactory.getCustomerDao();
//        System.out.println(customer.getClass());
//        customerDao.insert(customer);
//    }
    public static void getCustomerById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the id of the customer");
        int id = scanner.nextInt();
        UserDao customerDao = DaoFactory.getCustomerDao();
        User customer = customerDao.getUserById(id);
        System.out.println("Here is the customer you wanted: " + customer.toString());
    }

    public static User getCustomerByName(String name) {
        UserDao customerDao = DaoFactory.getCustomerDao();
        User customer = customerDao.getUserByName(name);
        return customer;
    }


    public static void getAllCustomers() {
        System.out.println("All customers:");
        UserDao customerDao = DaoFactory.getCustomerDao();
        List<User> customers = customerDao.getAllUsers();
        for (User customer : customers) {
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

    public static void deposit(int actTo, double amount) {
        if (amount > 0) {
            Transaction t = new Transaction(actTo, "deposit", amount, "approved");
            TransactionDao transactionDao = DaoFactory.getTransactionDao();
            transactionDao.insert(t);
            AccountDao accountDao = DaoFactory.getAccountDao();
            Account a = accountDao.getAccountByNumber(actTo);
            double newBalance = a.getBalance() + amount;
            a.setBalance(newBalance);
            accountDao.update(a);
        } else {
            System.out.println("You can't deposit negative amount");
        }
    }

    public static void withdraw(int actFrom, double amount) {
        AccountDao accountDao = DaoFactory.getAccountDao();
        Account a = accountDao.getAccountByNumber(actFrom);
        if (amount < a.getBalance()) {
            Transaction t = new Transaction("withdraw", actFrom, amount, "approved");
            TransactionDao transactionDao = DaoFactory.getTransactionDao();
            transactionDao.insert(t);
            double newBalance = a.getBalance() - amount;
            a.setBalance(newBalance);
            System.out.println("Your balance now is: " + a.getBalance());
            accountDao.update(a);
        } else {
            System.out.println("No wherewithal, buddy\n");
        }
    }

    public static void transfer(int actFrom, int actTo, double amount) {
        AccountDao accountDao = DaoFactory.getAccountDao();
        Account a = accountDao.getAccountByNumber(actFrom);
        if (a.getBalance() >= amount) {
            Account b = accountDao.getAccountByNumber(actTo);

            Transaction t = new Transaction(actFrom, actTo, "transfer", amount, "pending");
            TransactionDao transactionDao = DaoFactory.getTransactionDao();
            transactionDao.insert(t);

            double newBalanceFrom = a.getBalance() - amount;
            double newBalanceTo = b.getBalance() + amount;
            a.setBalance(newBalanceFrom);
            b.setBalance(newBalanceTo);
            accountDao.update(a);
            accountDao.update(b);
            System.out.println(amount + " transferred to account " + actTo + "\n");
        } else {
            System.out.println("No wherewithal, buddy\n");
        }
    }

}
