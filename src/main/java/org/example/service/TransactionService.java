package org.example.service;

import org.example.dao.AccountDao;
import org.example.dao.DaoFactory;
import org.example.dao.TransactionDao;
import org.example.entity.Account;
import org.example.entity.Transaction;

import java.util.Scanner;

public class TransactionService {

    public static void insertTransaction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the type of the transaction");
        String tType = scanner.nextLine();
        System.out.println("Please enter your account number");
        int actFrom = scanner.nextInt();
        System.out.println("Please enter who are you sending money to");
        int actTo = scanner.nextInt();
        System.out.println("And the amount");
        double amount = scanner.nextDouble();
        Transaction transaction = new Transaction(actFrom, actTo, tType, amount);

        TransactionDao transactionDao = DaoFactory.getTransactionDao();
        System.out.println(transaction.getClass());
        transactionDao.insert(transaction);
    }
}
