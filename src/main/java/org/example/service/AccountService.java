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
        System.out.println("You need 100usd to start with");
        Account account = new Account(actType, 100, "pending", userId);

        AccountDao accountDao = DaoFactory.getAccountDao();
        System.out.println(account.getClass());
        accountDao.insert(account);
    }

    public static void getAccountByNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the account number");
        int actNumber = scanner.nextInt();
        AccountDao accountDao = DaoFactory.getAccountDao();
        Account account = accountDao.getAccountByNumber(actNumber);
        System.out.println("Here is the account you wanted: " + account.toString());
    }

    public static void getPendingAccounts() {
        System.out.println("Here are all of the pending accounts:");
        AccountDao accountDao = DaoFactory.getAccountDao();
        List<Account> accounts = accountDao.getPendingAccounts();
        for(Account account : accounts) {
            System.out.println(account);
        }
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
