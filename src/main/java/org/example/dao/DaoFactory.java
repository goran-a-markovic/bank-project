package org.example.dao;

public class DaoFactory {
    private static UserDao customerDao;
    private static UserDao employeeDao;
    private static AccountDao accountDao;

    private DaoFactory() {

    }
    public static UserDao getCustomerDao() {
        if (customerDao == null) {
            customerDao = new CustomerDaoImpl();
        }
        return customerDao;
    }
    public static UserDao getEmployeeDao() {
        if (employeeDao == null) {
            employeeDao = new EmployeeDaoImpl();
        }
        return employeeDao;
    }
    public static AccountDao getAccountDao() {
        if (accountDao == null) {
            accountDao = new AccountDaoImpl();
        }
        return accountDao;
    }
}
