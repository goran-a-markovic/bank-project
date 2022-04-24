package org.example;

import org.example.dao.AccountDaoImpl;
import org.example.dao.DaoFactory;
import org.example.dao.UserDao;
import org.example.entity.Customer;
import org.example.entity.User;
import org.example.service.AccountService;
import org.example.service.CustomerService;
import org.example.service.EmployeeService;
import org.example.service.TransactionService;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static boolean loggedIn = false;
    public static int currentUserId;

    public static void main( String[] args ) {
//        CustomerService.insertCustomer();
//        EmployeeService.insertEmployee();
//        AccountService.openAccount();
//        TransactionService.insertTransaction();

//        EmployeeService.getEmployeeById();

//        CustomerService.deleteCustomer();
//        CustomerService.getAllCustomers();

//        CustomerService.updateCustomer();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi, choose one of the options");
        System.out.println("1 - Register");
        System.out.println("2 - Customer login");
        System.out.println("3 - Employee Login");
        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                CustomerService.insertCustomer();
                break;
            case 2:
                System.out.println("Please enter the name of the user");
                scanner.nextLine();
                String loginName = scanner.nextLine();
                System.out.println("Please enter the password");
                String loginPassword = scanner.nextLine();
                Customer.login(loginName, loginPassword);
                break;
            default:
                System.out.println("Choose smarter");
                break;
        }
    }
}
