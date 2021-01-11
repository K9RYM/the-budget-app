package com.kapps.budget.Model;


import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Account.class, parentColumns = "accountName", childColumns = "account"),
        @ForeignKey(entity = Month.class, parentColumns = "monthNumber", childColumns = "month")})
public class Record {

    public Record(double Amount, char Type, String Description, String Account, long Time, int Day, int Month) {
        amount = Amount;
        description = Description;
        recordType = Type;
        this.time = Time;
        this.account = Account;
        day = Day;
        month = Month;
    }

    @PrimaryKey(autoGenerate = true)
    int recordID;

    public int getRecordID() {
        return recordID;
    }

    @ColumnInfo
    private double amount;

    public double getAmount() {
        return amount;
    }


    @ColumnInfo
    private char recordType;

    @ColumnInfo
    public char getRecordType() {
        return recordType;
    }

    @Embedded
    private String account;

    public String getAccount() {
        return account;
    }

    @ColumnInfo
    private long time;

    public long getTime() {
        return time;
    }

    @ColumnInfo
    private String description;

    public String getDescription() {
        return description;
    }

    @ColumnInfo
    private int day;

    public int getDay() {
        return day;
    }

    @ColumnInfo
    private int month;

    public int getMonth() {
        return month;
    }
}
