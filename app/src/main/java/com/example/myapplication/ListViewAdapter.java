package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private Context con;
    public ArrayList<MoneyTransaction> transactionList;

    public ListViewAdapter(Context context, ArrayList<MoneyTransaction> transactionList) {
        super();
        this.transactionList = transactionList;
        this.con = context;
    }

    public void remove(int position) {
        transactionList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return transactionList.size();
    }

    @Override
    public Object getItem(int position) {
        return transactionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = null;

        if (convertView == null) {
            row = ((LayoutInflater) this.con.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.activity_listview, null);
        }
        else {
            row = convertView;
        }

        MoneyTransaction currentItem = (MoneyTransaction) getItem(position);

        TextView textViewLV1 = (TextView) row.findViewById(R.id.textViewLV1);
        TextView textViewLV2 = (TextView) row.findViewById(R.id.textViewLV2);
        TextView textViewLV3 = (TextView) row.findViewById(R.id.textViewLV3);
        TextView textViewLV4 = (TextView) row.findViewById(R.id.textViewLV4);

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

        return row;
    }
}
