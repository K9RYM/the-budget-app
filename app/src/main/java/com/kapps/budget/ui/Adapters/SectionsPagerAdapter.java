package com.kapps.budget.ui.Adapters;

import android.content.Context;

import com.kapps.budget.ui.main.CalendarFragment;
import com.kapps.budget.ui.main.MainFragment;

import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public static String currentDate = "0";

    private static final String[] TAB_TITLES = new String[]{currentDate, "Calendar"};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        currentDate = Calendar.getInstance().getTime().toString();
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a MainFragment (defined as a static inner class below).
        if (position == 1)
            return CalendarFragment.newInstance();
        else
            return MainFragment.newInstance(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}