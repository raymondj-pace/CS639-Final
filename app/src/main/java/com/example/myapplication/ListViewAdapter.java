package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<MoneyTransaction> {

    private ArrayList<MoneyTransaction> transactionList;
    private Context mContext;

    public ListViewAdapter(Context context, ArrayList<MoneyTransaction> transactionList) {
        super(context, 0, transactionList);

        this.transactionList = transactionList;
        this.mContext = context;
    }

    @SuppressWarnings("unused")
    public void remove(int position) {
        transactionList.remove(position);
        notifyDataSetChanged();
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
        TextView textViewLV5 = convertView.findViewById(R.id.textViewLV5);

        textViewLV1.setText("$");
        if (currentItem.getTransactionType() == 0) {
            textViewLV1.setTextColor(Color.parseColor("#FFEE4B2B"));
        }
        else {
            textViewLV1.setTextColor(Color.parseColor("#FF2DB83D"));
        }

        textViewLV2.setText(currentItem.getDate());

        double amount = currentItem.getAmount();
        DecimalFormat df = new DecimalFormat("0.00");
        String a = "$" + df.format(Math.abs(amount));
        textViewLV3.setText(a);

        textViewLV4.setText(currentItem.getDescription());

        textViewLV5.setText("X");


        textViewLV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("item", currentItem);

                if (currentItem.getTransactionType() == 1) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_AddFunds, bundle);
                }
                else if (currentItem.getTransactionType() == 0) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_Deduction, bundle);
                }
            }
        });

        textViewLV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("item", currentItem);

                if (currentItem.getTransactionType() == 1) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_AddFunds, bundle);
                }
                else if (currentItem.getTransactionType() == 0) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_Deduction, bundle);
                }
            }
        });

        textViewLV3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("item", currentItem);

                if (currentItem.getTransactionType() == 1) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_AddFunds, bundle);
                }
                else if (currentItem.getTransactionType() == 0) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_Deduction, bundle);
                }
            }
        });

        textViewLV4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("item", currentItem);

                if (currentItem.getTransactionType() == 1) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_AddFunds, bundle);
                }
                else if (currentItem.getTransactionType() == 0) {
                    Navigation.findNavController(v).navigate(R.id.action_Main_to_Deduction, bundle);
                }
            }
        });

        textViewLV5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Confirm")
                        .setMessage("Do you really want to delete?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                remove(position);
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        return convertView;
    }

}
