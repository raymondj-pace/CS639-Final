package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainFragment extends Fragment {

    DatabaseReference myRef;

    private static final String TAG = "MainFragment";

    private long snapShotRowCount = 0;

    private ListView simpleList;

    private View view;

    String[] transactionStrings;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);

        // Code here to populate list
        Log.d(TAG, "End Fragment Main");

        simpleList = view.findViewById(R.id.simpleListView);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("transactions");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(TAG, dataSnapshot.toString());

                if (simpleList == null) {
                    Log.d(TAG, "SIMPLE LIST IS NULL!!!!");
                } else {
                    Log.d(TAG, "simple list is okay");
                }

                snapShotRowCount = dataSnapshot.getChildrenCount();
                Log.d(TAG, "Count is: " + snapShotRowCount);
                List<MoneyTransaction> transactions = new ArrayList<>();

                for (DataSnapshot postSnapshot1 : dataSnapshot.getChildren()) {
                    MoneyTransaction t = postSnapshot1.getValue(MoneyTransaction.class);
                    transactions.add(t);
                }

                Collections.sort(transactions);

                transactionStrings = new String[(int) snapShotRowCount];
                int c = 0;
                for (MoneyTransaction t : transactions) {
                    transactionStrings[c] = t.toString();
                    Log.d(TAG, t.toString());
                    c = c + 1;
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(), R.layout.activity_listview, R.id.textView, transactionStrings);
                simpleList.setAdapter(arrayAdapter);
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
