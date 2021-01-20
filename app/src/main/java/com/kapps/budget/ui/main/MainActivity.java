package com.kapps.budget.ui.main;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.kapps.budget.Model.Account;
import com.kapps.budget.Model.Record;
import com.kapps.budget.Model.RecordsDB;
import com.kapps.budget.R;
import com.kapps.budget.ui.Adapters.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    /**
     * Model references
     */
    protected RecordsDB mRecordsDB;
    /**
     * Records for a single day (usually today) with an average of 10 records:
     **/
    private ArrayList<Record> mDayRecords;

    public ArrayList<Record> getDayRecords() {
        return mDayRecords;
    }

    /**
     * Records for a single month (usually current month on calendar) with an average of 300 records
     **/
    private ArrayList<Record> mMonthRecords;

    public ArrayList<Record> getMonthRecords() {
        return mMonthRecords;
    }

    /**
     * user's Accounts
     **/
    private List<Account> mMyAccounts;

    public List<Account> getmMyAccounts() {
        return mMyAccounts;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRecordsDB = Room.databaseBuilder(getApplicationContext(), RecordsDB.class, RecordsDB.DBNAME).fallbackToDestructiveMigration().build();
    }

    private ArrayList<Record> getRecordsForDay(int day, int month) {
        //TODO
        return null;
    }

    private ArrayList<Record> getRecordsForMonth(int month) {
        //TODO
        return null;
    }
}