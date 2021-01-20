package com.kapps.budget.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Record.class, Month.class, Account.class}, version = 1, exportSchema = false)
public abstract class RecordsDB extends RoomDatabase {
    public abstract RecordsDAO recordsDAO();

    public abstract MonthsDAO monthsDAO();

    public abstract AccountsDAO accountsDAO();

    private static RecordsDB INSTANCE;

    public static final String DBNAME = "RecordsDB";
//    public static final String DBLOCATION = String.format("/",)

    public static RecordsDB getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RecordsDB.class, "RecordsDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static RecordsDB getMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), RecordsDB.class)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
