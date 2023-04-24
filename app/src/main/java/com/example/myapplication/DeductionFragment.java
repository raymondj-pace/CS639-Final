package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DeductionFragment extends Fragment {

    // Fragment fields
    private EditText amount;
    private EditText description;
    private CalendarView calendarView;

    // Calendar field values
    private String currentYear;
    private String currentMonth;
    private String currentDay;

    private String transKey = null;
    final private int transactionType = 0;
    private static final String TAG = "DeductFundsFragment";

    DatabaseReference myRef;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View fragmentDeductionLayout = inflater.inflate(R.layout.fragment_deduction, container, false);

        amount = fragmentDeductionLayout.findViewById(R.id.editTextNumberDecimal2);
        description = fragmentDeductionLayout.findViewById(R.id.editTextDescription2);
        calendarView = fragmentDeductionLayout.findViewById(R.id.calendarView2);

        Bundle bundle = getArguments();
        MoneyTransaction _trans = null;
        if (bundle != null) {
            _trans = bundle.getParcelable("item");
        }
        if (_trans != null) {
            Log.d("BUNDLE", "Bundle is not null");
            Log.d("BUNDLE", bundle.toString());
            Log.d("BUNDLE", _trans.getKey());
            Log.d("BUNDLE", String.valueOf(_trans.getTransactionType()));
            Log.d("BUNDLE", _trans.getDescription());
            Log.d("BUNDLE", _trans.getDate());
            Log.d("BUNDLE", String.valueOf(_trans.getAmount()));

            String _key = _trans.getKey();
            int _trans_type = _trans.getTransactionType();
            String _desc = _trans.getDescription();
            String _date = _trans.getDate();
            double _amount = _trans.getAmount();

            DecimalFormat df = new DecimalFormat("0.00");
            amount.setText(df.format(Math.abs(_amount)));
            description.setText(_desc);
            this.transKey = _key;

            DateTimeFormatter date_f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate l_date = LocalDate.parse(_date, date_f);
            int month = l_date.getMonthValue();
            int year = l_date.getYear();
            int day = l_date.getDayOfMonth();
            this.currentDay = String.valueOf(day);
            if (month < 10) {
                currentMonth = "0" + month;
            }
            else {
                this.currentMonth = String.valueOf(month);
            }
            this.currentYear = String.valueOf(year);

            try {
                calendarView.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(_date).getTime(), true, true);
            } catch (ParseException e) {
                // Leave as default date
                //throw new RuntimeException(e);
            }
        }
        else {

            Log.d("BUNDLE", "Bundle is NULL");

            this.transKey = null;

            /*
             * Set default date in case nothing is selected from CalendarView
             */
            Date today = new Date(); // Fri Jun 17 14:54:28 PDT 2016
            Calendar cal = Calendar.getInstance();
            cal.setTime(today); // don't forget this if date is arbitrary e.g. 01-01-2014

            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);

            if (month < 10) {
                currentMonth = "0" + month;
            }
            else {
                currentMonth = String.valueOf(month);
            }

            if (dayOfMonth < 10) {
                currentDay = "0" + dayOfMonth;
            }
            else {
                currentDay = String.valueOf(dayOfMonth);
            }

            currentYear = String.valueOf(year);
        }


        /*
         * Setup listener on description field so keyboard can be hidden when <Enter> key is pressed
         */
        description.setOnEditorActionListener((v, actionId, event) -> {
            // Hide keyboard
            if (actionId == 0) {  // Shouldn't be zero but it's the only thing that works
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        });


        /*
         * Listen for calender selection changes
         */
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {

            // Month is zero based
            month += 1;

            // Put a leading 0 on day and month when needed
            if (month < 10) {
                currentMonth = "0" + month;
            }
            else {
                currentMonth = String.valueOf(month);
            }

            if (dayOfMonth < 10) {
                currentDay = "0" + dayOfMonth;
            }
            else {
                currentDay = String.valueOf(dayOfMonth);
            }

            currentYear = String.valueOf(year);

            Log.d(TAG,currentYear + "-" + currentMonth + "-" + currentDay);
        });

        return fragmentDeductionLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
         * Validate user fields and submit to Firebase if all data is present and return to parent fragment
         */
        view.findViewById(R.id.DeductFunds_BtnOk).setOnClickListener(view1 -> {
            if (deductingFunds()) {
                NavHostFragment.findNavController(DeductionFragment.this)
                        .navigate(R.id.action_Deduction_to_Main);
            }
        });


        /*
         * Return to parent fragment when Cancel is pressed
         */
        view.findViewById(R.id.DeductFunds_BtnCancel).setOnClickListener(view12 -> NavHostFragment.findNavController(DeductionFragment.this)
                .navigate(R.id.action_Deduction_to_Main));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public boolean deductingFunds() {

        Log.d(TAG, "Starting to add transaction");

        // Get the search string from the input field.
        String aAmount = amount.getText().toString();
        String aDescription = description.getText().toString();


        if (aAmount.isEmpty() || aDescription.isEmpty()) {

            if (aAmount.isEmpty()) {
                amount.setHint("Please enter Amount");
            }

            if (aDescription.isEmpty()) {
                description.setHint("Please enter Description");
            }

            Activity activity = getActivity();
            Toast.makeText(activity,"Enter Amount, Description, and Date", Toast.LENGTH_LONG).show();

            return false;
        }
        else {

            String dateStr = this.currentYear + "-" + this.currentMonth + "-" + this.currentDay;

            double amount = Double.parseDouble(aAmount);

            // Get Firebase insert reference
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            myRef = database.getReference("transactions");

            /*
             * Update Firebase record
             */
            if (this.transKey != null) {
                Log.d("UPDATE UPDATE", "Performing update on " + this.transKey);
                MoneyTransaction transaction = new MoneyTransaction(this.transKey, this.transactionType, dateStr, amount, aDescription);
                myRef.child(this.transKey).setValue(transaction);
            }

            /*
             * Create new Firebase record
             */
            else {
                // Create new transaction
                MoneyTransaction transaction = new MoneyTransaction(this.transactionType, dateStr, amount, aDescription);
                DatabaseReference newPostRef  = myRef.push();
                newPostRef.setValue(transaction);
            }

            return true;
        }
    }
}
