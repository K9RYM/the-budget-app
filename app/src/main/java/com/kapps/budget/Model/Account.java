package com.kapps.budget.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Accounts")
public class Account {

    public Account(String Name, double Balance, String Currency) {
        accountName = Name;
        balance = Balance;
        currency = Currency;

    }


    @PrimaryKey
    public String accountName;


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
