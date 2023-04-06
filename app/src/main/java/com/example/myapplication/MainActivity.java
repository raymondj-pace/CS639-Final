package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    DatabaseReference myRef;

    private static final String TAG = "MyActivity";

    private long snapShotRowCount = 0;

    private ListView simpleList;

    String[] transactionStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        myRef = database.getReference("employees");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(TAG, dataSnapshot.toString());

                snapShotRowCount = dataSnapshot.getChildrenCount();
                Log.d(TAG, "Count is: " + snapShotRowCount);
                List<MoneyTransaction> transactions = new ArrayList<>();

                for (DataSnapshot postSnapshot1: dataSnapshot.getChildren()) {
                    MoneyTransaction t = postSnapshot1.getValue(MoneyTransaction.class);
                    transactions.add(t);
                }

                Collections.sort(transactions);

                transactionStrings = new String[(int) snapShotRowCount];
                int c = 0;
                for (MoneyTransaction t: transactions) {
                    transactionStrings[c] = t.toString();
                    Log.d(TAG, t.toString());
                    c = c + 1;
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.activity_listview, R.id.textView, transactionStrings);
                simpleList.setAdapter(arrayAdapter);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}