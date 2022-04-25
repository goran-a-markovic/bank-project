package org.example.dao;

import org.example.entity.Account;

import java.util.List;

public interface AccountDao {

    public void insert(Account account);
    public Account getAccountByNumber(int actNumber);

    List<Account> getPendingAccounts();

    public void approveAccount(int actNumber, String decision);
}

