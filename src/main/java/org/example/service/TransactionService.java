package org.example.service;

import org.example.dao.AccountDao;
import org.example.dao.DaoFactory;
import org.example.dao.TransactionDao;
import org.example.dao.UserDao;
import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.entity.User;

import java.util.List;
import java.util.Scanner;

public class TransactionService {

    public static void insertTransaction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the type of the transaction");
        String tType = scanner.nextLine();
        System.out.println("Please enter your account number");
        int actFrom = scanner.nextInt();
        System.out.println(actFrom);
        System.out.println("Please enter who are you sending money to");
        int actTo = scanner.nextInt();
        System.out.println(actFrom);
        System.out.println("And the amount");
        double amount = scanner.nextDouble();
        String status = "pending";
        Transaction transaction = new Transaction(actFrom, actTo, tType, amount, status);

        TransactionDao transactionDao = DaoFactory.getTransactionDao();
        transactionDao.insert(transaction);
    }

    public static void getAllTransactions() {
        System.out.println("Log of all transactions:");
        TransactionDao transactionDao = DaoFactory.getTransactionDao();
        List<Transaction> transactions = transactionDao.getAllTransactions();
        for(Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static void getPendingTransactions(int actNumber) {
        TransactionDao transactionDao = DaoFactory.getTransactionDao();
        List<Transaction> transactions = transactionDao.getPendingTransactions(actNumber);
        if (!transactions.isEmpty()) {
            System.out.println("Here are all of yours pending transactions:");
            for (Transaction transaction : transactions) {
                System.out.println("ID of the transaction: " + transaction.getId() + "; Account number from: " + transaction.getActFrom() + "; Amount: " + transaction.getAmount());
            }
            TransactionService.approveTransaction();
        } else {
            System.out.println("There is no pending transaction at the moment");
        }
    }

    public static void approveTransaction() {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        System.out.println("What is the ID of the transaction you would like to approve?");
        int id = scannerInt.nextInt();
        System.out.println("Do you want to approve or reject?");
        String decision = scanner.nextLine();
        TransactionDao transactionDao = DaoFactory.getTransactionDao();
        transactionDao.approveTransaction(id, decision);
    }
}
