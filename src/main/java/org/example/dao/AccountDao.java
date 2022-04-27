package org.example.dao;

import org.example.entity.Account;

import java.util.List;

public interface AccountDao {

    public void insert(Account account);
    public Account getAccountByNumber(int actNumber);

    List<Account> getAllAccounts();

    List<Account> getPendingAccounts();

    List<Account> getCustomersAccounts(int userId);

    public void approveAccount(int actNumber, String decision);
    void update(Account account);

}

