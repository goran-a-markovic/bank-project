package org.example.dao;

import org.example.ConnectionFactory;
import org.example.entity.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    Connection connection;

    public TransactionDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(Transaction transaction) {
        String sql = "INSERT INTO trans (id, actFrom, amount, tType, actTo, status) values (default, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, transaction.getActFrom());
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.setString(3, transaction.getTType());
            preparedStatement.setInt(4, transaction.getActTo());;
            preparedStatement.setString(5, transaction.getStatus());
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int id = resultSet.getInt(1);
            } else {
                System.out.println("Something went wrong when running your transaction!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
    @Override
    public Transaction getTransactionById(int id) {
        String sql = "select * from trans where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Transaction transaction = getTransaction(resultSet);
                return transaction;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//
    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        String sql = "select * from trans;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Transaction transaction = getTransaction(resultSet);
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public Transaction getTransaction(ResultSet resultSet) {
        try {
            int idData = resultSet.getInt("id");
            int actFrom = resultSet.getInt("actFrom");
            int actTo = resultSet.getInt("actTo");
            String tType = resultSet.getString("tType");
            double amount = resultSet.getDouble("amount");
            String status = resultSet.getString("status");
            return new Transaction(idData, actFrom, actTo, tType, amount, status);
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }

    @Override
    public List<Transaction> getPendingTransactions(int actNumber) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        String sql = "select * from trans where status = 'pending' and actTo = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, actNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Transaction transaction = getTransaction(resultSet);
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public void approveTransaction(int id, String decision) {
        String sql = "update trans set status = ? where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, decision);
            preparedStatement.setInt(2, id);
            int count = preparedStatement.executeUpdate();
            if (count == 1) System.out.println("Update successful!");
            else System.out.println("Something went wrong with the update");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setNewBalance(double newBalance, int actNumber) {
        String sql = "update account set balance = ? where actNumber = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setInt(2, actNumber);
            int count = preparedStatement.executeUpdate();
            if (count == 1) System.out.println("Update successful!");
            else System.out.println("Something went wrong with the update");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    @Override
//    public void update(Book book) {
//        String sql = "update book set name = ?, author = ?, description = ?, year = ? where id = ?;";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, book.getName());
//            preparedStatement.setString(2, book.getAuthor());
//            preparedStatement.setString(3, book.getDescription());
//            preparedStatement.setInt(4, book.getYear());
//            preparedStatement.setInt(5, book.getId());
//            int count = preparedStatement.executeUpdate();
//            if (count == 1) System.out.println("Update successful!");
//            else System.out.println("Something went wrong with the update");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
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

    //    @Override
//    public void depositToA(int actNumber, String decision) {
//        String sql = "update account set status = ? where actNumber = ?;";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, decision);
//            preparedStatement.setInt(2, actNumber);
//            int count = preparedStatement.executeUpdate();
//            if (count == 1) System.out.println("Update successful!");
//            else System.out.println("Something went wrong with the update");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
