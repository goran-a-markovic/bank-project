package org.example.dao;

import org.example.ConnectionFactory;
import org.example.entity.Account;
import org.example.entity.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDaoImpl implements TransactionDao {

    Connection connection;

    public TransactionDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(Transaction transaction) {
        String sql = "INSERT INTO trans (id, actFrom, amount, tType, actTo) values (default, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, transaction.getActFrom());
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.setString(3, transaction.getTType());
            preparedStatement.setInt(4, transaction.getActTo());
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                System.out.println("Transaction added successfully!");
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int id = resultSet.getInt(1);
                System.out.println("Generated ID is: " + id);
            } else {
                System.out.println("Something went wrong when creating a transaction!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
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
//    @Override
//    public List<Book> getAllBooks() {
//        List<Book> books = new ArrayList<Book>();
//        String sql = "select * from book;";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()) {
//                Book book = getBook(resultSet);
//                books.add(book);
//            }
//            return books;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return books;
//    }
//
//    public Book getBook(ResultSet resultSet) {
//        try {
//            int idData = resultSet.getInt("id");
//            String name = resultSet.getString("name");
//            String author = resultSet.getString("author");
//            String description = resultSet.getString("description");
//            int year = resultSet.getInt("year");
//            return new Book(idData, name, author, description, year);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } return null;
//    }
//
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
}
