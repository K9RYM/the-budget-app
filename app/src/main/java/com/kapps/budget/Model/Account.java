package com.kapps.budget.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {

    public Account(String Name, double Balance, String Currency) {
        accountName = Name;
        balance = Balance;
        currency = Currency;

    }


    @PrimaryKey
    private String accountName;


    public String getAccountName() {
        return accountName;
    }

    @ColumnInfo
    private double balance;

    public double getBalance() {
        return balance;
    }

    @ColumnInfo
    private String currency;

    public String getCurrency() {
        return currency;
    }
}
