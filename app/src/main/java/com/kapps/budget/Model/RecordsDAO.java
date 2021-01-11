package com.kapps.budget.Model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface RecordsDAO {


    @Query("INSERT INTO Records (amount,type,description,account,time,day,month) VALUES (:Amount,:Type,:Description,:AccountName,:Time,:Day,:Month)")
    void CreateRecord(double Amount, char Type, String Description, String AccountName, long Time, int Day, int Month);

    @Query("UPDATE Records SET amount = :Amount , type = :Type ,description = :Description, account = :AccountName WHERE recordID = :recordID")
    void EditRecord(double Amount, char Type, String Description, String AccountName, int recordID);

    @Query("DELETE FROM Records WHERE recordID = :recordID")
    void DeleteRecord(int recordID);

    @Query("SELECT * FROM Records WHERE month=:MonthNum")
    List<Record> getRecordsforMonth(int MonthNum);

    @Query("SELECT * FROM Records WHERE month=:MonthNum and day=:Day")
    List<Record> getRecordsforDay(int MonthNum, int Day);

    @Query("INSERT INTO Accounts (accountName,balance,currency) VALUES(:AccountName,:Balance,:Currency)")
    void CreateAccount(String AccountName, double Balance, String Currency);

    @Query("DELETE FROM Accounts WHERE accountName = :AccountName")
    void DeleteAccount(String AccountName);

    @Query("SELECT * FROM Accounts")
    List<Account> getMyAccounts();

    @Query("INSERT INTO Months (monthNumber,mainWallet,reserveMoney,bankAccount,target) VALUES(:monthNumber,:mainWallet,:reserveMoney,:bankAccount,:target)")
    void CreateMonthRecord(int monthNumber, double mainWallet, double reserveMoney, double bankAccount, int target);

    @Query("UPDATE Months SET mainWallet = :mainWallet , reserveMoney = :reserveMoney ,bankAccount = :bankAccount, target = :target WHERE monthNumber = :monthNumber")
    void EditRecord(int monthNumber, double mainWallet, double reserveMoney, double bankAccount, int target);

    @Query("SELECT * FROM Months WHERE monthNumber=:montNumber")
    List<Month> getMonthInfo(int montNumber);

}
