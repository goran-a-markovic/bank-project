package org.example;

import org.example.dao.AccountDaoImpl;
import org.example.service.AccountService;
import org.example.service.CustomerService;
import org.example.service.EmployeeService;
import org.example.service.TransactionService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
//        CustomerService.insertCustomer();
//        EmployeeService.insertEmployee();
//        AccountService.openAccount();
        TransactionService.insertTransaction();
    }
}
