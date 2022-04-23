package org.example;

import org.example.dao.AccountDaoImpl;
import org.example.service.AccountService;
import org.example.service.CustomerService;
import org.example.service.EmployeeService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        AccountService.openAccount();
    }
}
