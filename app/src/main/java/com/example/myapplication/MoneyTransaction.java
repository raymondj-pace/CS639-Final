package com.example.myapplication;

import static com.example.myapplication.TransactionType.ADD;
import static com.example.myapplication.TransactionType.SUBTRACT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class MoneyTransaction implements Comparable<MoneyTransaction> {

    private int transactionType;
    private String description;
    //private LocalDate date;
    private String date;
    private double amount;

    private final DateTimeFormatter displayDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private final DateTimeFormatter sortDateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

    public MoneyTransaction() {
        /*
        this.transType = ADD;
        this.date = null;
        this.amount = 0.00;
        this.description = "none";
         */
    }

    MoneyTransaction(/*TransactionType*/ int trans_type, /*LocalDate*/ String date, Double amount, String description) {
        this.transactionType = trans_type;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public int /*TransactionType*/ getTransType() {
        return transactionType;
    }

    // public LocalDate getDate() {
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

    public void setTransactionType(String t) {
        if (t.compareTo("-") == 0) {
            this.transactionType = 0;  //SUBTRACT;
        }
        else {
            this.transactionType = 1;  //ADD;
        }
    }

    public void setTransactionType(int t) {
        if (t == 0) {
            this.transactionType = 0;  //SUBTRACT;
        }
        else {
            this.transactionType = 1;  //ADD;
        }
    }

    // public void setDate(LocalDate d) { this.date = d; }

    public void setDate(String d) {
        this.date = d;
        /*
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);
        this.date = LocalDate.parse(d, formatter);

         */
    }

    public void setDescription(String d) {
        this.description = d;
    }

    public void setAmount(double amt) { this.amount = amt; }


    @Override
    public int compareTo(MoneyTransaction trans) {

        // Sort the objects by their dates in format: YYYY MM DD

        String str1 = this.date;
        String str2 = trans.date;
        /*
        String str1 = sortDateFormat.format(this.date);
        String str2 = sortDateFormat.format(trans.date);
*       */

        return str1.compareTo(str2);
    }

    @Override
    public String toString() {

        if (this.transactionType == 1) {
            return "+" + "\t" + this.date + "\t$" + String.valueOf(this.amount) + "\t" + this.description;
        }

        // else
        return "-" + "\t" + this.date + "\t$" + String.valueOf(this.amount) + "\t" + this.description;

        /*
        if (this.transType == ADD) {
            return "+" + "\t" + displayDateFormat.format(this.date) + "\t$" + String.valueOf(this.amount) + "\t" + this.description;
        }

        // else
        return "-" + "\t" + displayDateFormat.format(this.date) + "\t$" + String.valueOf(this.amount) + "\t" + this.description;
         */
    }
}
