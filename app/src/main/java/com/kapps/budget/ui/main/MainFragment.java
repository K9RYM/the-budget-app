package com.kapps.budget.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.kapps.budget.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    //-------------------------------------------------
    //**UI elements**
    //-------------------------------------------------
    TextView mTodayTXT;
    EditText mAmountTXT;
    EditText mDescriptionTXT;
    Button mEntryBTN;
    MaterialButtonToggleGroup mSegmentBtn;
    MaterialButton mSpendingBtn;
    MaterialButton mIncomeBtn;
    Spinner mAccountSPN;
    RecyclerView mEntriesViewRC;


    //-------------------------------------------------
    //**Fields**
    //-------------------------------------------------
    private int mSegCheckId;

    private int recordMode = 0;


    public static final String LOG_TAG = "Main fragment";
    public static final String INIT_FAILED = "Main fragment";


    //-------------------------------------------------
    //**Constructors**
    //-------------------------------------------------

    public static MainFragment newInstance(int index) {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    //-------------------------------------------------
    //**Lifecycle Methods**
    //-------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View Main = inflater.inflate(R.layout.fragment_main, container, false);
        return Main;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            InitUI(view);
        } catch (Exception e) {
            Log.e(LOG_TAG + ":", INIT_FAILED + ":" + e.toString());
        }

        InitDefaultState();

        mSegmentBtn.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                ModeButtonSwitch(checkedId);
            }
        });

    }
    //-------------------------------------------------
    //**End of lifecycle Methods**
    //-------------------------------------------------

    //-------------------------------------------------
    //**Helper Methods**
    //-------------------------------------------------
    private void InitDefaultState() {
        mSegCheckId = mSpendingBtn.getId();
        mSpendingBtn.setChecked(true);
        mIncomeBtn.setChecked(false);
        recordMode = 0;
        mSpendingBtn.setBackgroundColor(getResources().getColor(R.color.spendingRed));
    }

    //flip colors only one toggling buttons
    private void ModeButtonSwitch(int checkedId) {
        if (mSegCheckId != checkedId) {
            //checkincome is true when income btn pressed
            if (checkedId == mIncomeBtn.getId()) {
                mIncomeBtn.setStrokeColorResource(R.color.incomeGreen);
                mIncomeBtn.setBackgroundColor(getResources().getColor(R.color.incomeGreen));
                mIncomeBtn.setTextColor(getResources().getColor(R.color.defaultBlack));
                mSpendingBtn.setStrokeColorResource(R.color.incomeGreen);
                mSpendingBtn.setBackgroundColor(getResources().getColor(R.color.transparent));
                mSpendingBtn.setTextColor(getResources().getColor(R.color.spendingRed));

                //un-toggle the other button to implement radio toggle
                mSpendingBtn.setChecked(false);
                mIncomeBtn.setChecked(true);
                recordMode = 1;
            } else {
                mIncomeBtn.setStrokeColorResource(R.color.spendingRed);
                mIncomeBtn.setBackgroundColor(getResources().getColor(R.color.transparent));
                mIncomeBtn.setTextColor(getResources().getColor(R.color.incomeGreen));
                mSpendingBtn.setStrokeColorResource(R.color.spendingRed);
                mSpendingBtn.setBackgroundColor(getResources().getColor(R.color.spendingRed));
                mSpendingBtn.setTextColor(getResources().getColor(R.color.defaultBlack));

                //un-toggle the other button to implement radio toggle
                mSpendingBtn.setChecked(true);
                mIncomeBtn.setChecked(false);
                recordMode = 0;
            }
            //track checked btn id
            mSegCheckId = checkedId;
        }
    }


    private void InitUI(View view) {
        mAmountTXT = view.findViewById(R.id.entryAmount_txt);
        mEntryBTN = view.findViewById(R.id.enter_bt);
        mSegmentBtn = view.findViewById(R.id.segmentBtn);
        mSpendingBtn = view.findViewById(R.id.spending_sgb);
        mIncomeBtn = view.findViewById(R.id.income_sgb);

    }

    //-------------------------------------------------
    //**End of helper Methods**
    //-------------------------------------------------
}