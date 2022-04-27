package org.example.service;

import org.example.App;
import org.example.dao.AccountDao;
import org.example.dao.DaoFactory;
import org.example.dao.UserDao;
import org.example.entity.Account;
import org.example.entity.Customer;
import org.example.entity.User;

import java.util.List;
import java.util.Scanner;

public class AccountService {

    public static void openAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the type of the account");
        String actType = scanner.nextLine();
        int userId;
        if (App.loggedIn == false) {
            System.out.println("Please enter the user id");
            userId = scanner.nextInt();
        } else {
            userId = App.currentUserId;
        }
        System.out.println("You need 100 usd to start with");
        Account account = new Account(actType, 100, "pending", userId);

        AccountDao accountDao = DaoFactory.getAccountDao();
        accountDao.insert(account);
        System.out.println("Your account needs to get approved. It usually takes less than a minute. Please come back later.");
    }

    public static void getAccountByNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the account number");
        int actNumber = scanner.nextInt();
        AccountDao accountDao = DaoFactory.getAccountDao();
        Account account = accountDao.getAccountByNumber(actNumber);
        System.out.println("The owner's ID is: " + account.getUserId());
        System.out.println("The balance is: " + account.getBalance() + "\n");
    }

    public static void getAccountByNumber(int actNumber) {
        AccountDao accountDao = DaoFactory.getAccountDao();
        Account account = accountDao.getAccountByNumber(actNumber);
    }

    public static void getAllAccounts() {
        System.out.println("Here are all of the accounts:");
        AccountDao accountDao = DaoFactory.getAccountDao();
        List<Account> accounts = accountDao.getAllAccounts();
        for (Account account : accounts) {
            System.out.println("Account number - " + account.getActNumber() + "\n");
        }
    }

    public static void getPendingAccounts() {
        AccountDao accountDao = DaoFactory.getAccountDao();
        List<Account> accounts = accountDao.getPendingAccounts();
        if (!accounts.isEmpty()) {
            System.out.println("Here are all of the pending accounts:");
            for (Account account : accounts) {
                System.out.println("Account number: " + account.getActNumber() + "; Owner ID: " + account.getUserId()+ "\n");
            }
            AccountService.approveAccount();
        } else {
            System.out.println("There is no pending accounts at the moment" + "\n");
        }
    }

    public static void getCustomersAccounts(int userId) {
        System.out.println("Here are all of your accounts:");
        AccountDao accountDao = DaoFactory.getAccountDao();
        List<Account> accounts = accountDao.getCustomersAccounts(userId);
        for (Account account : accounts) {
            System.out.println("Account number - " + account.getActNumber());
            System.out.println("Status - " + account.getStatus());
            System.out.println("Balance - " + account.getBalance() + "\r\n");
        }
        System.out.println("You can only use the ones that are approved.");
    }

    public static void approveAccount() {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        System.out.println("What is the number of the account you would like to update?");
        int actNumber = scannerInt.nextInt();
        System.out.println("Do you want to approve or reject?");
        String decision = scanner.nextLine();
        AccountDao accountDao = DaoFactory.getAccountDao();
        accountDao.approveAccount(actNumber, decision);
    }


}
