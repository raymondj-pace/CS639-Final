package com.example.myapplication;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.format.DateTimeFormatter;


public class MoneyTransaction implements Parcelable, Comparable<MoneyTransaction> {

    private String key;
    private int transactionType;
    private String description;
    private String date;
    private double amount;

    @SuppressWarnings("unused")
    private final DateTimeFormatter displayDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    @SuppressWarnings("unused")
    private final DateTimeFormatter sortDateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

    /*
     * To implement Parcelable
     *
     */
    public static final Creator<MoneyTransaction> CREATOR = new Creator<MoneyTransaction>() {
        @Override
        public MoneyTransaction createFromParcel(Parcel in) {
            return new MoneyTransaction(in);
        }

        @Override
        public MoneyTransaction[] newArray(int size) {
            return new MoneyTransaction[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /*
     * WARNING WARNING WARNING:
     * The order of items in writeToParcel MUST MATCH the order
     * in the Constructor that uses Parcel as the input
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);
        parcel.writeInt(this.transactionType);
        parcel.writeString(this.description);
        parcel.writeString(this.date);
        parcel.writeDouble(this.amount);
    }

    public MoneyTransaction(Parcel in) {
        this.key = in.readString();
        this.transactionType = in.readInt();
        this.description = in.readString();
        this.date = in.readString();
        this.amount = in.readDouble();
    }

    @SuppressWarnings("unused")
    public MoneyTransaction() {}

    @SuppressWarnings("unused")
    MoneyTransaction(int trans_type, String date, Double amount, String description) {
        this.transactionType = trans_type;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.key = "";
    }

    @SuppressWarnings("unused")
    MoneyTransaction(String key, int trans_type, String date, Double amount, String description) {
        this.key = key;
        this.transactionType = trans_type;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    @SuppressWarnings("unused")
    public String getKey() { return this.key; }

    @SuppressWarnings("unused")
    public void setKey(String k) { this.key = k; }

    @SuppressWarnings("unused")
    public int getTransactionType() {
        return transactionType;
    }

    @SuppressWarnings("unused")
    public String getTransactionType_String() {
        if (this.transactionType == 0) {
            return "-";
        }
        return "+";
    }

    @SuppressWarnings("unused")
    public String getDate() {
        //return displayDateFormat.format(this.date);
        return this.date;
    }

    public double getAmount() { return amount; }

    public String getDescription() { return description; }

    @SuppressWarnings("unused")
    public void setTransactionType_String(String t) {
        if (t.compareTo("-") == 0) {
            this.transactionType = 0;
        }
        else {
            this.transactionType = 1;
        }
    }

    @SuppressWarnings("unused")
    public void setTransactionType(int t) {
        if (t == 0) {
            this.transactionType = 0;
        }
        else {
            this.transactionType = 1;
        }
    }

    @SuppressWarnings("unused")
    public void setDate(String d) {
        this.date = d;
    }

    @SuppressWarnings("unused")
    public void setDescription(String d) {
        this.description = d;
    }

    @SuppressWarnings("unused")
    public void setAmount(double amt) {
        this.amount = amt;
    }

    @Override
    public int compareTo(MoneyTransaction trans) {

        // Sort the objects by their dates in format: YYYY MM DD in Descending order
        String str1 = this.date;
        String str2 = trans.date;

        // Swap value so that it's in descending order
        return -str1.compareTo(str2);
    }

    @NonNull
    @Override
    public String toString() {

        if (this.transactionType == 1) {
            return "+" + "\t" + this.date + "\t$" + this.amount + "\t" + this.description;
        }

        // else
        return "-" + "\t" + this.date + "\t$" + this.amount + "\t" + this.description;
    }
}