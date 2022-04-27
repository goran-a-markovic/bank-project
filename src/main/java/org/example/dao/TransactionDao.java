package org.example.dao;

import org.example.entity.Transaction;

import java.sql.ResultSet;
import java.util.List;

public interface TransactionDao {

    public void insert(Transaction transaction);

    //
    Transaction getTransactionById(int id);

    List<Transaction> getAllTransactions();

    public Transaction getTransaction(ResultSet resultSet);

    List<Transaction> getPendingTransactions(int actNumber);

    void approveTransaction(int id, String decision);

    void setNewBalance(double newBalance, int actNumber);


    //
    //    @Override
    //    public Book getBookById(int id) {
    //        String sql = "select * from book where id = ?;";
    //        try {
    //            PreparedStatement preparedStatement = connection.prepareStatement(sql);
    //            preparedStatement.setInt(1, id);
    //            ResultSet resultSet = preparedStatement.executeQuery();
    //            if (resultSet.next()) {
    //                Book book = getBook(resultSet);
    //                return book;
    //            }
    //        } catch (SQLException e) {
    //            e.printStackTrace();
    //        }
    //        return null;
    //    }
    //

}
