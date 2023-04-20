package com.example.myapplication;

import android.app.Activity;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AddFundsFragment extends Fragment {

    private EditText amount;
    private EditText description;
    private CalendarView calendarView;

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

        return fragmentAddFundsLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.AddFunds_BtnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addingFunds(view);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }

    public void addingFunds(View view) {

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
        }
        else {
            // Format date
            Long date_epoch = calendarView.getDate();
            Date d = new Date(date_epoch);
            String pattern = "yyyy-MM-dd";
            DateFormat df = new SimpleDateFormat(pattern);
            String date_str = df.format(d);


            // Format amount to ###.##
            double amount = Double.parseDouble(aAmount);

            // Create new transaction
            MoneyTransaction transaction = new MoneyTransaction(1, date_str, amount, aDescription);

            // Get Firebase insert reference
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            myRef = database.getReference("transactions");

            DatabaseReference newPostRef  = myRef.push();

            newPostRef.setValue(transaction);

            NavHostFragment.findNavController(AddFundsFragment.this)
                    .navigate(R.id.action_AddFunds_to_Main);
        }

        // Hide keyboard
        /*
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
        */
    }
}
