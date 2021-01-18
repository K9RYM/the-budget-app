package com.kapps.budget.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Months")
public class Month {

    public Month(int monthNumber, double mainWallet, double reserveMoney, double bankAccount, int target) {
        this.monthNumber = monthNumber;
        this.bankAccount = bankAccount;
        this.mainWallet = mainWallet;
        this.target = target;
        this.reserveMoney = reserveMoney;
    }

    @PrimaryKey(autoGenerate = true)
    private int monthNumber;

    public int getMonthNumber() {
        return monthNumber;
    }

    @ColumnInfo
    private double mainWallet;

    public double getMainWallet() {
        return mainWallet;
    }

    @ColumnInfo
    private double reserveMoney;

    public double getReserveMoney() {
        return reserveMoney;
    }

    @ColumnInfo
    private double bankAccount;

    public double getBankAccount() {
        return bankAccount;
    }

    @ColumnInfo
    private int target;

    public int getTarget() {
        return target;
    }
}
