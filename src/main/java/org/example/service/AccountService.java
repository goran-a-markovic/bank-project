package org.example.service;

import org.example.App;
import org.example.dao.AccountDao;
import org.example.dao.DaoFactory;
import org.example.dao.UserDao;
import org.example.entity.Account;
import org.example.entity.Customer;

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
}
