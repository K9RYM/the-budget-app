package com.kapps.budget.Interfaces;

import com.kapps.budget.Model.Record;

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
    List<Record> getRecordsforMonth( int MonthNum );

    @Query("SELECT * FROM Records WHERE month=:MonthNum and day=:Day")
    List<Record> getRecordsforDay(int MonthNum, int Day);

}
