package org.example;

import org.example.dao.AccountDaoImpl;
import org.example.dao.DaoFactory;
import org.example.dao.UserDao;
import org.example.entity.Account;
import org.example.entity.Customer;
import org.example.entity.Employee;
import org.example.entity.User;
import org.example.service.AccountService;
import org.example.service.CustomerService;
import org.example.service.EmployeeService;
import org.example.service.TransactionService;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static boolean loggedIn = false;
    public static int currentUserId;
    public static int currentEmpId;
    public static boolean flag = true;
    public static boolean flagEmp = true;
    public static boolean flagUser = false;

    public static void main(String[] args) {
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

        while (flag) {
            switch (choice) {
                case 1:
                    CustomerService.insertCustomer();
                case 2: //customer options
                    System.out.println("Log In");
                    System.out.println("Please enter the name of the user");
                    scanner.nextLine();
                    String loginName = scanner.nextLine();
                    System.out.println("Please enter the password");
                    String loginPassword = scanner.nextLine();
                    Customer.login(loginName, loginPassword);
                    while (flagUser) {
                        System.out.println("What would you like to do now?");
                        System.out.println("1 - Apply for an account");
                        System.out.println("2 - Withdraw money");
                        System.out.println("3 - Deposit money");
                        System.out.println("4 - Transfer money");
                        System.out.println("5 - Check my balance");
                        System.out.println("6 - Check my pending transactions");
                        System.out.println("7 - Log out");
                        int custChoice = scanner.nextInt();
                        switch (custChoice) {
                            case 1:
                                AccountService.openAccount();
                                flag = false;
                                flagUser = false;
                                break;
                            case 2:
                                AccountService.getCustomersAccounts(App.currentUserId);

                                System.out.println("What is the account number you want to withdraw from?");
                                int actToWithdraw = scanner.nextInt();
                                System.out.println("How much money would you like to withdraw?");
                                double amtToWithdraw = scanner.nextDouble();
                                CustomerService.withdraw(actToWithdraw, amtToWithdraw);
                                break;
                            case 3:
                                AccountService.getCustomersAccounts(App.currentUserId);
                                System.out.println("What is the account number you want to deposit to?");
                                int actToDeposit = scanner.nextInt();
                                System.out.println("How much money would you like to deposit?");
                                double amtToDeposit = scanner.nextDouble();
                                CustomerService.deposit(actToDeposit, amtToDeposit);
                                break;
                            case 4:
                                AccountService.getCustomersAccounts(App.currentUserId);
                                System.out.println("What is the account number you want to transfer from?");
                                int actTransferFrom = scanner.nextInt();
                                System.out.println("What is the account number you want to transfer to?");
                                int actTransferTo = scanner.nextInt();
                                System.out.println("How much money we're talking about?");
                                double amtToTransfer = scanner.nextDouble();
                                CustomerService.transfer(actTransferFrom, actTransferTo, amtToTransfer);
                                break;
                            case 5:
                                AccountService.getAccountByNumber();
                                break;
                            case 6:
                                System.out.println("What is the account number you want to see transactions to?");
                                int actTransactions = scanner.nextInt();
                                TransactionService.getPendingTransactions(actTransactions);
                                break;
                            case 7:
                                flag = false;
                                flagUser = false;
                                break;
                            default:
                                System.out.println("Choose smarter");
                                break;
                        }
                    }
                    break;
                case 3: //employee options
                    System.out.println("Please enter your name");
                    scanner.nextLine();
                    String empName = scanner.nextLine();
                    System.out.println("Please enter your password");
                    String empPassword = scanner.nextLine();
                    Employee.login(empName, empPassword);
                    while (flagEmp) {
                        System.out.println("Welcome, what do you want to do now?");
                        System.out.println("1 - View an account");
                        System.out.println("2 - Approve an account");
                        System.out.println("3 - See transactions log");
                        System.out.println("4 - Log out");
                        int empChoice = scanner.nextInt();
                        switch (empChoice) {
                            case 1:
                                AccountService.getAllAccounts();
                                AccountService.getAccountByNumber();
                                System.out.println("\r\n");
                                break;
                            case 2:
                                AccountService.getPendingAccounts();
                                break;
                            case 3:
                                TransactionService.getAllTransactions();
                                break;
                            case 4:
                                flagEmp = false;
                                flag = false;
                                currentEmpId = 0;
                                System.out.println("Logging out");
                                break;
                            default:
                                System.out.println("Choose smarter");
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("Choose smarter");
                    break;
            }

        }
    }
}
