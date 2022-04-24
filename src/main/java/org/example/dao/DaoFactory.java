package org.example.dao;

public class DaoFactory {
    private static UserDao customerDao;
    private static UserDao employeeDao;
    private static AccountDao accountDao;
    private static TransactionDao transactionDao;

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
    public static TransactionDao getTransactionDao() {
        if (transactionDao == null) {
            transactionDao = new TransactionDaoImpl();
        }
        return transactionDao;
    }
}
