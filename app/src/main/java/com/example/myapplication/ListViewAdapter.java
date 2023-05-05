package com.example.myapplication;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import com.zerobranch.layout.SwipeLayout;

public class ListViewAdapter extends ArrayAdapter<MoneyTransaction> {

    private final ArrayList<MoneyTransaction> transactionList;
    @SuppressWarnings("unused")
    private final Context mContext;

    public ListViewAdapter(Context context, ArrayList<MoneyTransaction> transactionList) {
        super(context, 0, transactionList);

        this.transactionList = transactionList;
        this.mContext = context;
    }

    @SuppressWarnings("unused")
    public void remove(int position) {

        // Get the Firebase key
        MoneyTransaction trans = transactionList.get(position);
        String key = trans.getKey();

        // Remove from adapter
        transactionList.remove(position);
        notifyDataSetChanged();

        // Delete from Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("transactions");
        myRef.child(key).setValue(null);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @SuppressWarnings("unused")
    class Holder {
        TextView dragItem;
        ImageView rightView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview, parent, false);
        }

        MoneyTransaction currentItem = getItem(position);

        @SuppressWarnings("unused")
        LinearLayout dragItem = convertView.findViewById(R.id.drag_item);

        ImageView rightView = convertView.findViewById(R.id.right_view);
        SwipeLayout swipeLayout = convertView.findViewById(R.id.swipe_layout);
        TextView textViewLV1 = convertView.findViewById(R.id.textViewLV1);
        TextView textViewLV2 = convertView.findViewById(R.id.textViewLV2);
        TextView textViewLV3 = convertView.findViewById(R.id.textViewLV3);
        TextView textViewLV4 = convertView.findViewById(R.id.textViewLV4);
        TextView textViewLV2ndRow = convertView.findViewById(R.id.textViewLV2ndRow);

        textViewLV1.setText("$");
        if (currentItem.getTransactionType() == 0) {
            textViewLV1.setTextColor(ContextCompat.getColor(mContext, R.color.warning_red));
        }
        else {
            textViewLV1.setTextColor(ContextCompat.getColor(mContext, R.color.dollar_green));
        }

        textViewLV2.setText(currentItem.getDate());
        double amount = currentItem.getAmount();
        DecimalFormat df = new DecimalFormat("0.00");
        String a = "$" + df.format(Math.abs(amount));
        textViewLV3.setText(a);
        textViewLV4.setText(currentItem.getDescription());
        textViewLV2ndRow.setText(currentItem.getDescription());


        textViewLV1.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("item", currentItem);

            if (currentItem.getTransactionType() == 1) {
                Navigation.findNavController(v).navigate(R.id.action_Main_to_AddFunds, bundle);
            }
            else if (currentItem.getTransactionType() == 0) {
                Navigation.findNavController(v).navigate(R.id.action_Main_to_Deduction, bundle);
            }
        });

        textViewLV2.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("item", currentItem);

            if (currentItem.getTransactionType() == 1) {
                Navigation.findNavController(v).navigate(R.id.action_Main_to_AddFunds, bundle);
            }
            else if (currentItem.getTransactionType() == 0) {
                Navigation.findNavController(v).navigate(R.id.action_Main_to_Deduction, bundle);
            }
        });

        textViewLV3.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("item", currentItem);

            if (currentItem.getTransactionType() == 1) {
                Navigation.findNavController(v).navigate(R.id.action_Main_to_AddFunds, bundle);
            }
            else if (currentItem.getTransactionType() == 0) {
                Navigation.findNavController(v).navigate(R.id.action_Main_to_Deduction, bundle);
            }
        });

        textViewLV4.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("item", currentItem);

            if (currentItem.getTransactionType() == 1) {
                Navigation.findNavController(v).navigate(R.id.action_Main_to_AddFunds, bundle);
            }
            else if (currentItem.getTransactionType() == 0) {
                Navigation.findNavController(v).navigate(R.id.action_Main_to_Deduction, bundle);
            }
        });

        /* noinspection
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
        });*/

        if (rightView != null) {
            rightView.setOnClickListener(v -> {
                if (position != NO_POSITION) {
                    remove(position);
                }
            });
        }

        swipeLayout.setOnActionsListener(new SwipeLayout.SwipeActionsListener() {
            @Override
            public void onOpen(int direction, boolean isContinuous) {
                if(direction == SwipeLayout.LEFT && isContinuous){
                    remove(position);
                }
            }

            @Override
            public void onClose() {

            }
        });

        return convertView;
    }
}
