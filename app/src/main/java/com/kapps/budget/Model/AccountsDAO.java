package com.kapps.budget.Model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface AccountsDAO {
    @Query("INSERT INTO Accounts (accountName,balance,currency) VALUES(:AccountName,:Balance,:Currency)")
    void CreateAccount(String AccountName, double Balance, String Currency);

    @Query("DELETE FROM Accounts WHERE accountName = :AccountName")
    void DeleteAccount(String AccountName);

    @Query("SELECT * FROM Accounts")
    List<Account> getMyAccounts();
}
