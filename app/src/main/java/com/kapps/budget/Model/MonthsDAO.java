package com.kapps.budget.Model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface MonthsDAO {

    @Query("INSERT INTO Months (monthNumber,mainWallet,reserveMoney,bankAccount,target) VALUES(:monthNumber,:mainWallet,:reserveMoney,:bankAccount,:target)")
    void CreateMonthRecord(int monthNumber, double mainWallet, double reserveMoney, double bankAccount, int target);

    @Query("UPDATE Months SET mainWallet = :mainWallet , reserveMoney = :reserveMoney ,bankAccount = :bankAccount, target = :target WHERE monthNumber = :monthNumber")
    void EditRecord(int monthNumber, double mainWallet, double reserveMoney, double bankAccount, int target);

    @Query("SELECT * FROM Months WHERE monthNumber=:montNumber")
    List<Month> getMonthInfo(int montNumber);
}
