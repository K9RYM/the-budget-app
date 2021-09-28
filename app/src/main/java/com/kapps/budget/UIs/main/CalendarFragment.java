package com.kapps.budget.UIs.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kapps.budget.R;
import com.marcohc.robotocalendar.RobotoCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {


    final String UI_INIT_LOAD_FAILED = "UI";


    RobotoCalendarView mCalendarView;

    TextView mSpendings_tv;
    TextView mIncome_tv;
    TextView mWallet_tv;
    TextView mSpendingsMonth_tv;
    TextView mIncomeMonth_tv;
    TextView mWalletMonth_tv;
    TextView mAverage_tv;
    TextView mSTD_tv;
    TextView mTarget_tv;

    public CalendarFragment() {
        // Required empty public constructor
    }

    //    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment CalendarFragment.
//     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance() {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            initUI(view);

        } catch (Exception e) {
            Log.e(UI_INIT_LOAD_FAILED, e.toString());
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat();

    }


    private void initUI(View view) {

        mCalendarView = view.findViewById(R.id.calendarView);

        mSpendings_tv = view.findViewById(R.id.calDaySpending_tv);
        mSpendingsMonth_tv = view.findViewById(R.id.calMthSpending_tv);
        mIncome_tv = view.findViewById(R.id.calDayIncome_tv);
        mIncomeMonth_tv = view.findViewById(R.id.calMthIncome_tv);
        mWallet_tv = view.findViewById(R.id.calDayWallet_tv);
        mWalletMonth_tv = view.findViewById(R.id.calMthWallet_tv);
        mWallet_tv = view.findViewById(R.id.calDayWallet_tv);
        mWalletMonth_tv = view.findViewById(R.id.calMthWallet_tv);
        mTarget_tv = view.findViewById(R.id.calMthTarget_tv);

    }
}