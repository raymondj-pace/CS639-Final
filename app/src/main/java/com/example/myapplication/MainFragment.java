package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentMainBinding;
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
    private ArrayList<MoneyTransaction> transactions;
    private ListViewAdapter adapter;
    private Context mContext;
    private FragmentMainBinding binding;

    public static boolean isEqual(double d0, double d1) {
        final double epsilon = 0.0000001;
        return d0 == d1 ? true : Math.abs(d0 - d1) < epsilon;
    }

    // Save the context here onAttach - it's not guaranteed to be non-null onViewCreated !!!
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        simpleList = view.findViewById(R.id.simpleListView);

        simpleList.addHeaderView(new View(this.mContext), null, true);
        simpleList.addFooterView(new View(this.mContext), null, true);

        transactions = new ArrayList<>();

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button2).setOnClickListener(view1 -> {
            MainFragmentDirections.ActionMainToAddFunds action = MainFragmentDirections.actionMainToAddFunds();
            NavHostFragment.findNavController(MainFragment.this).navigate(action);
        });

        view.findViewById(R.id.button3).setOnClickListener(view12 -> {
            MainFragmentDirections.ActionMainToDeduction action = MainFragmentDirections.actionMainToDeduction();
            NavHostFragment.findNavController(MainFragment.this).navigate(action);
        });

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

                    t.setKey(postSnapshot1.getKey());

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
                DecimalFormat df = new DecimalFormat("0.00");

                Log.d("CURRENT BALANCE", String.valueOf(currentBalance));

                if (isEqual(currentBalance, 0.00)) {
                    Log.d("ISEQUAL", "TRUE");
                    balanceFormatted = "$" + df.format(0.00);
                    tv.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                }
                else if (currentBalance > 0.00) {
                    balanceFormatted = "$" + df.format(currentBalance);
                    tv.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                }
                else {
                    balanceFormatted = "-$" + df.format(Math.abs(currentBalance));
                    tv.setTextColor(ContextCompat.getColor(mContext, R.color.warning_red));
                }

                // Update the main balance
                tv.setText(balanceFormatted);

                // Sort and then show the listview
                Collections.sort(transactions);

                // Populate the listview
                adapter = new ListViewAdapter(mContext, transactions);
                simpleList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}