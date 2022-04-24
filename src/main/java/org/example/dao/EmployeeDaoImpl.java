package org.example.dao;

import org.example.ConnectionFactory;
import org.example.entity.Employee;
import org.example.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements UserDao {

    Connection connection;

    public EmployeeDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(User employee) {
        String sql = "INSERT INTO employee(id, name, password) VALUES(default, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPassword());
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                System.out.println("Employee added successfully!");
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

    //this is the downside of having just one UserDao, I had to implement a method I don't even need
    @Override
    public User getUserById(int id) {
        String sql = "select * from employee where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee employee = getEmployee(resultSet);
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employee getEmployee(ResultSet resultSet) {
        try {
            int idData = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            return new Employee(idData, name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }
//
    @Override
    public List<User> getAllUsers() {
        List<User> employees = new ArrayList<User>();
        String sql = "select * from employee;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Employee employee = getEmployee(resultSet);
                employees.add(employee);
            }
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
//

//
    @Override
    public void update(User employee) {
        System.out.println("Nothing to see here, just ignoring some methods");
    }
//
    @Override
    public void delete(int id) {
        String sql = "delete from employee where id = ?;";
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
