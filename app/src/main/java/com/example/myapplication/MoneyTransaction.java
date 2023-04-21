package com.example.myapplication;

import static com.example.myapplication.TransactionType.ADD;
import static com.example.myapplication.TransactionType.SUBTRACT;

import android.util.Log;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class MoneyTransaction implements Comparable<MoneyTransaction> {

    private String key;
    private int transactionType;
    private String description;
    //private LocalDate date;
    private String date;
    private double amount;

    @SuppressWarnings("unused")
    private final DateTimeFormatter displayDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    @SuppressWarnings("unused")
    private final DateTimeFormatter sortDateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

    @SuppressWarnings("unused")
    public MoneyTransaction() {
        /*
        this.transType = ADD;
        this.date = null;
        this.amount = 0.00;
        this.description = "none";
         */
    }

    @SuppressWarnings("unused")
    MoneyTransaction(/*TransactionType*/ int trans_type, /*LocalDate*/ String date, Double amount, String description) {
        this.transactionType = trans_type;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.key = "";
    }

    @SuppressWarnings("unused")
    public String getKey() { return this.key; }
    @SuppressWarnings("unused")
    public void setKey(String k) { this.key = k; }

    @SuppressWarnings("unused")
    public int /*TransactionType*/ getTransactionType() {
        return transactionType;
    }
    @SuppressWarnings("unused")
    public String getTransactionType_String() {
        if (this.transactionType == 0) {
            return "-";
        }
        return "+";
    }

    // public LocalDate getDate() {
    @SuppressWarnings("unused")
    public String getDate() {
        // return this.date;
        //return displayDateFormat.format(this.date);
        return this.date;
    }

    public double getAmount() { return amount; }

    public String getDescription() { return description; }


    /*
    public void setTransactionType(TransactionType t) {
        this.transactionType = t;
    }*/

    @SuppressWarnings("unused")
    public void setTransactionType_String(String t) {
        if (t.compareTo("-") == 0) {
            this.transactionType = 0;  //SUBTRACT;
        }
        else {
            this.transactionType = 1;  //ADD;
        }
    }

    @SuppressWarnings("unused")
    public void setTransactionType(int t) {
        if (t == 0) {
            this.transactionType = 0;  // SUBTRACT;
        }
        else {
            this.transactionType = 1;  // ADD;
        }
    }

    // public void setDate(LocalDate d) { this.date = d; }
    @SuppressWarnings("unused")
    public void setDate(String d) {
        this.date = d;
        /*
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);
        this.date = LocalDate.parse(d, formatter);
         */
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
        /*
        String str1 = sortDateFormat.format(this.date);
        String str2 = sortDateFormat.format(trans.date);
*       */

        // Swap value so that it's in descending order
        return -str1.compareTo(str2);
    }

    @NonNull
    @Override
    public String toString() {

        if (this.transactionType == 1) {
            return "+" + "\t" + this.date + "\t$" + String.valueOf(this.amount) + "\t" + this.description;
        }

        // else
        return "-" + "\t" + this.date + "\t$" + String.valueOf(this.amount) + "\t" + this.description;

        /*
        if (this.transactionType == ADD) {
            return "+" + "\t" + displayDateFormat.format(this.date) + "\t$" + String.valueOf(this.amount) + "\t" + this.description;
        }

        // else
        return "-" + "\t" + displayDateFormat.format(this.date) + "\t$" + String.valueOf(this.amount) + "\t" + this.description;
         */
    }
}
