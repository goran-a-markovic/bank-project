package org.example.dao;

import org.example.ConnectionFactory;
import org.example.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    Connection connection;

    public AccountDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(Account account) {
        String sql = "INSERT INTO account(actNumber, actType, balance, status, userId) VALUES(default, ?, 100, 'pending', ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getActType());
            preparedStatement.setInt(2, account.getUserId());
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                System.out.println("Account opened successfully!");
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int actNumber = resultSet.getInt(1);
                System.out.println("Your new account number is: " + actNumber);
            } else {
                System.out.println("Something went wrong when creating an account!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
    @Override
    public Account getAccountByNumber(int actNumber){
        String sql = "select * from account where actNumber = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, actNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = getAccount(resultSet);
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<Account>();
        String sql = "select * from account;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Account account = getAccount(resultSet);
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
    @Override
    public List<Account> getPendingAccounts() {
        List<Account> accounts = new ArrayList<Account>();
        String sql = "select * from account where status = 'pending';";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Account account = getAccount(resultSet);
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Account> getCustomersAccounts(int userId) {
        List<Account> accounts = new ArrayList<Account>();
        String sql = "select * from account where userId = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Account account = getAccount(resultSet);
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void approveAccount(int actNumber, String decision) {
        String sql = "update account set status = ? where actNumber = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, decision);
            preparedStatement.setInt(2, actNumber);
            int count = preparedStatement.executeUpdate();
            if (count == 1) System.out.println("Update successful!");
            else System.out.println("Something went wrong with the update");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//
    public Account getAccount(ResultSet resultSet) {
        try {
            int actNumber = resultSet.getInt("actNumber");
            String actType = resultSet.getString("actType");
            double balance = resultSet.getDouble("balance");
            String status = resultSet.getString("status");
            int userId = resultSet.getInt("userId");
            return new Account(actNumber, actType, balance, status, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }
//
    @Override
    public void update(Account account) {
        String sql = "update account set balance = ? where actNumber = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getActNumber());
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                System.out.println("Update successful!\n");
            }
            else System.out.println("Something went wrong with the update\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
//    @Override
//    public void delete(int id) {
//        String sql = "delete from book where id = ?;";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            int count = preparedStatement.executeUpdate();
//            if (count == 1) System.out.println("Delete successful!");
//            else System.out.println("Something went wrong with the deletion");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
