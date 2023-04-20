package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class AddFundsFragment extends Fragment {

    // Fragment fields
    private EditText amount;
    private EditText description;
    private CalendarView calendarView;

    // Calendar field values
    private String currentYear;
    private String currentMonth;
    private String currentDay;

    // Current String within the description field
    private String descriptionStr;

    private static final String TAG = "AddFundsFragment";

    DatabaseReference myRef;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View fragmentAddFundsLayout = inflater.inflate(R.layout.fragment_add_funds, container, false);

        amount = fragmentAddFundsLayout.findViewById(R.id.editTextNumberDecimal1);
        description = fragmentAddFundsLayout.findViewById(R.id.editTextDescription1);
        calendarView = fragmentAddFundsLayout.findViewById(R.id.calendarView1);


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
            currentMonth = "0" + String.valueOf(month);
        }
        else {
            currentMonth = String.valueOf(month);
        }

        if (dayOfMonth < 10) {
            currentDay = "0" + String.valueOf(dayOfMonth);
        }
        else {
            currentDay = String.valueOf(dayOfMonth);
        }

        currentYear = String.valueOf(year);


        /*
         * Setup listener on description field so keyboard can be hidden when <Enter> key is pressed
         */
        description.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == 0) {  // Shouldn't be zero but it's the only thing that works
                    Log.d(TAG, "HIT ENTER on Description");

                    // Save text and then hide keyboard
                    descriptionStr = description.getText().toString();
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });


        /*
         * Listen for calender selection changes
         */
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                // Month is zero based
                month += 1;

                // Put a leading 0 on day and month when needed

                if (month < 10) {
                    currentMonth = "0" + String.valueOf(month);
                }
                else {
                    currentMonth = String.valueOf(month);
                }

                if (dayOfMonth < 10) {
                    currentDay = "0" + String.valueOf(dayOfMonth);
                }
                else {
                    currentDay = String.valueOf(dayOfMonth);
                }

                currentYear = String.valueOf(year);

                Log.d(TAG,currentYear + "-" + currentMonth + "-" + currentDay);
            }
        });

        return fragmentAddFundsLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
         * Validate user fields and submit to Firebase if all data is present and return to parent fragment
         */
        view.findViewById(R.id.AddFunds_BtnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addingFunds(view)) {
                    NavHostFragment.findNavController(AddFundsFragment.this)
                            .navigate(R.id.action_AddFunds_to_Main);
                }
            }
        });


        /*
         * Return to parent fragment when Cancel is pressed
         */
        view.findViewById(R.id.AddFunds_BtnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddFundsFragment.this)
                        .navigate(R.id.action_AddFunds_to_Main);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }

    public boolean addingFunds(View view) {

        Log.d(TAG, "Starting to add transaction");

        // Get the search string from the input field.
        String aAmount = amount.getText().toString();
        String aDescription = description.getText().toString();


        if ( aAmount.isEmpty() || aDescription.isEmpty()) {

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

            // Format amount to ###.##
            double amount = Double.parseDouble(aAmount);
            DecimalFormat df = new DecimalFormat("0.00");
            String formatted = df.format(amount);
            amount = Double.parseDouble(formatted);



            // Create new transaction
            MoneyTransaction transaction = new MoneyTransaction(1, dateStr, amount, aDescription);

            // Get Firebase insert reference
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            myRef = database.getReference("transactions");

            DatabaseReference newPostRef  = myRef.push();

            newPostRef.setValue(transaction);

            return true;
        }
    }
}
