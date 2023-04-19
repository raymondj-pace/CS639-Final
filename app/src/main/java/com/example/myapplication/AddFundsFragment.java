package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
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

public class AddFundsFragment extends Fragment {

    private EditText amount;
    private EditText description;
    private CalendarView date;

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }

    /*public void addingFunds(View view) {
        // Get the search string from the input field.
        double amount =
        String lastName = mLastName.getText().toString();

        if (firstName.isEmpty() || lastName.isEmpty()) {

            if (firstName.isEmpty()) {
                mFirstName.setHint("Enter first name");
            }

            if (lastName.isEmpty()) {
                mLastName.setHint("Enter last name");
            }

            Toast toast = Toast.makeText(this, "Enter first and last names", Toast.LENGTH_SHORT);
            toast.show();

            return;
        }

        // Clear the previous inputs
        mFirstName.setText("");
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
        }
    }*/
}
