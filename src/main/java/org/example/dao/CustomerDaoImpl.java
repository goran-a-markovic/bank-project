package org.example.dao;

import org.example.ConnectionFactory;
import org.example.entity.Customer;
import org.example.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements UserDao {

    Connection connection;

    public CustomerDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(User customer) {
        String sql = "INSERT INTO customer(id, name, password) VALUES(default, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPassword());
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                System.out.println("Customer added successfully!");
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int id = resultSet.getInt(1);
                System.out.println("Generated ID is: " + id);
            } else {
                System.out.println("Something went wrong when creating a book!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
    @Override
    public User getUserById(int id) {
        String sql = "select * from customer where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = getCustomer(resultSet);
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Customer getUserByName(String name) {
        String sql = "select * from customer where name = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = getCustomer(resultSet);
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer getCustomer(ResultSet resultSet) {
        try {
            int idData = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            System.out.println("Usao");
            return new Customer(idData, name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }
    @Override
    public List<User> getAllUsers() {
        List<User> customers = new ArrayList<User>();
        String sql = "select * from customer;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Customer customer = getCustomer(resultSet);
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
//

//
    @Override
    public void update(User customer) {
        String sql = "update customer set name = ?, password = ? where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setInt(3, customer.getId());
            int count = preparedStatement.executeUpdate();
            if (count == 1) System.out.println("Update successful!");
            else System.out.println("Something went wrong with the update");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
    @Override
    public void delete(int id) {
        String sql = "delete from customer where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int count = preparedStatement.executeUpdate();
            if (count == 1) System.out.println("Delete successful!");
            else System.out.println("Something went wrong with the deletion");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
