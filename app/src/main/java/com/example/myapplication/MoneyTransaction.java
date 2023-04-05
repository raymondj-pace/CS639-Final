package com.example.myapplication;

import static com.example.myapplication.TransactionType.ADD;
import static com.example.myapplication.TransactionType.SUBTRACT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class MoneyTransaction implements Comparable<MoneyTransaction> {

    private TransactionType transType;
    private String description;
    private LocalDate date;
    private double amount;

    private final DateTimeFormatter displayDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private final DateTimeFormatter sortDateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

    MoneyTransaction(TransactionType trans_type, LocalDate date, Double amount, String description) {
        this.transType = trans_type;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public TransactionType getTransType() {
        return transType;
    }

    public LocalDate getDate() { return this.date; }

    public double getAmount() { return amount; }

    public String getDescription() { return description; }


    public void setTransactionType(TransactionType t) {
        this.transType = t;
    }

    public void setDate(LocalDate d) { this.date = d; }

    public void setDescription(String d) {
        this.description = d;
    }

    public void setAmount(double amt) { this.amount = amt; }


    @Override
    public int compareTo(MoneyTransaction trans) {

        // Sort the objects by their dates in format: YYYY MM DD

        String str1 = sortDateFormat.format(this.date);
        String str2 = sortDateFormat.format(trans.date);

        return str1.compareTo(str2);
    }

    @Override
    public String toString() {

        if (this.transType == ADD) {
            return "+" + "\t" + displayDateFormat.format(this.date) + "\t$" + String.valueOf(this.amount) + "\t" + this.description;
        }

        // else
        return "-" + "\t" + displayDateFormat.format(this.date) + "\t$" + String.valueOf(this.amount) + "\t" + this.description;
    }
}
