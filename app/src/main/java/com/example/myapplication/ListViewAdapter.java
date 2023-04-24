package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.navigation.Navigation;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<MoneyTransaction> {

    private ArrayList<MoneyTransaction> transactionList;

    public ListViewAdapter(Context context, ArrayList<MoneyTransaction> transactionList) {
        super(context, 0, transactionList);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview, parent, false);
        }

        MoneyTransaction currentItem = getItem(position);

        TextView textViewLV1 = convertView.findViewById(R.id.textViewLV1);
        TextView textViewLV2 = convertView.findViewById(R.id.textViewLV2);
        TextView textViewLV3 = convertView.findViewById(R.id.textViewLV3);
        TextView textViewLV4 = convertView.findViewById(R.id.textViewLV4);

        if (currentItem.getTransactionType() == 0) {
            textViewLV1.setText("-");
        }
        else {
            textViewLV1.setText("+");
        }

        textViewLV2.setText(currentItem.getDate());

        double amount = currentItem.getAmount();
        DecimalFormat df = new DecimalFormat("0.00");
        String a = "$" + df.format(Math.abs(amount));
        textViewLV3.setText(a);

        textViewLV4.setText(currentItem.getDescription());

        textViewLV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LV1", "CLICKED");
                if (currentItem.getTransactionType() == 1) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_AddFunds);
                }
                else if (currentItem.getTransactionType() == 0) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_Deduction);
                }
            }
        });

        textViewLV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LV2", "CLICKED");
                if (currentItem.getTransactionType() == 1) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_AddFunds);
                }
                else if (currentItem.getTransactionType() == 0) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_Deduction);
                }
            }
        });

        textViewLV3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LV3", "CLICKED");
                if (currentItem.getTransactionType() == 1) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_AddFunds);
                }
                else if (currentItem.getTransactionType() == 0) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_Deduction);
                }
            }
        });

        textViewLV4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LV4", "CLICKED");

                if (currentItem.getTransactionType() == 1) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_AddFunds);
                }
                else if (currentItem.getTransactionType() == 0) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_Deduction);
                }
            }
        });

        View view = convertView;
        //TextView textView = view.findViewById(R.id.textView);
        //textView.setText(items.get(position));

        // Set the tag of the View to the position of the item in the list
        view.setTag(position);

        // Set the onTouchListener of the View to handle the swipe event
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Get the action of the event
                int action = event.getAction();

                // If the action is a swipe to the left, delete the item at the current position
                if (action == MotionEvent.ACTION_UP && event.getX() < v.getWidth() / 2) {
                    remove(position);
                }

                return false;
            }
        });

        return convertView;
    }

    // Remove the item at the specified position from the list
    public void remove(int position) {
        transactionList.remove(position);
        notifyDataSetChanged();
    }
}
