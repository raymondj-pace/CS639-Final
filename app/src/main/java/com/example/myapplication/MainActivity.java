package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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

import com.example.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    /*
    DatabaseReference myRef;

    private static final String TAG = "MyActivity";

    private long snapShotRowCount = 0;

    private ListView simpleList;

    String[] transactionStrings;
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.fragment_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("employees");

        // ????? binding.


        /*
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

         */
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}