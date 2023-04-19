package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;

import java.time.LocalDate;
import java.util.Date;

public class AddFundsFragment extends Fragment {

    private EditText amount;
    private EditText description;
    private CalendarView date;
    private static final String TAG = "MyActivity";
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View fragmentAddFundsLayout = inflater.inflate(R.layout.fragment_add_funds, container, false);

        amount = fragmentAddFundsLayout.findViewById(R.id.editTextNumberDecimal1);
        description = fragmentAddFundsLayout.findViewById(R.id.editTextDescription1);
        date = fragmentAddFundsLayout.findViewById(R.id.calendarView1);

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
        Log.d(TAG,"Error in Ok button call for Add funds");
        // Get the search string from the input field.
        String aAmount = amount.getText().toString();
        String aDescription = description.getText().toString();
        Date tempDate = new Date(date.getDate());
        String aDate = tempDate.toString();

        if ( aAmount.isEmpty() || aDescription.isEmpty() || aDate.isEmpty()) {

             if (aAmount.isEmpty()) {
               amount.setHint("Please enter Amount");
            }

            if (aDescription.isEmpty()) {
                description.setHint("Please enter Description");
            }

            //TODO: Look for date how to set it's hint.
            /* (aDate.isEmpty()) {
                date.setHint("Please select Date");
            }*/

            Activity activity = getActivity();
            Toast.makeText(activity,"Enter Amount, Description, and Date", Toast.LENGTH_LONG).show();
            return;
        }

        // Clear the previous inputs
        /* mFirstName.setText("");
        mLastName.setText("");

        // Reset Hint values
        mFirstName.setHint("Enter first name");
        mLastName.setHint("Enter last name");

        // Get a reference to where new entry will go - this avoids having to guess the offset
        DatabaseReference newPostRef  = myRef.push();
        Employee emp = new Employee(lastName, firstName);
        newPostRef.setValue(emp);

        // Return focus back to first name edit text
        mFirstName.requestFocus();

        // Hide keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }*/
    }
}
