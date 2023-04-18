package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    DatabaseReference myRef;
    private long snapShotRowCount = 0;
    private ListView simpleList;
    private View view;
    private ArrayList<MoneyTransaction> transactions;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);

        // Code here to populate list
        Log.d(TAG, "End Fragment Main");

        simpleList = view.findViewById(R.id.simpleListView);
        transactions = new ArrayList<MoneyTransaction>();

        /*
         *  Read Firebase contents
         */
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("transactions");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                simpleList.setAdapter(null);  // Be sure to clear contents
                transactions.clear();

                Log.d(TAG, dataSnapshot.toString());

                snapShotRowCount = dataSnapshot.getChildrenCount();
                Log.d(TAG, "Database Count is: " + snapShotRowCount);

                double currentBalance = 0.00;
                for (DataSnapshot postSnapshot1 : dataSnapshot.getChildren()) {
                    MoneyTransaction t = postSnapshot1.getValue(MoneyTransaction.class);
                    transactions.add(t);
                    if (t.getTransactionType() == 0) {
                        currentBalance -= t.getAmount();
                    }
                    else {
                        currentBalance += t.getAmount();
                    }
                }

                TextView tv = view.findViewById(R.id.textView3);

                String balanceFormatted;
                DecimalFormat df = new DecimalFormat("#.00");
                if (currentBalance < 0.0) {
                    balanceFormatted = "-$" + df.format(Math.abs(currentBalance));
                    tv.setTextColor(Color.parseColor("#FF0000"));  // Red
                }
                else {
                    balanceFormatted = "$" + df.format(currentBalance);
                    tv.setTextColor(Color.parseColor("#000000"));  // Black
                }

                // Update the main balance
                tv.setText(balanceFormatted);

                // Sort and then show the listview
                Collections.sort(transactions);

                // Populate the listview
                ListViewAdapter adapter = new ListViewAdapter(getActivity(), transactions);
                simpleList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragmentDirections.ActionMainToAddFunds action = MainFragmentDirections.actionMainToAddFunds(0);
                NavHostFragment.findNavController(MainFragment.this).navigate(action);
            }
        });

        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragmentDirections.ActionMainToDeduction action = MainFragmentDirections.actionMainToDeduction(0);
                NavHostFragment.findNavController(MainFragment.this).navigate(action);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }
}
