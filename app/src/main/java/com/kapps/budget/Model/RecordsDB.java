package com.kapps.budget.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Record.class, Month.class, Account.class}, version = 1, exportSchema = false)
public abstract class RecordsDB extends RoomDatabase {
    public abstract RecordsDAO recordsDAO();
}
