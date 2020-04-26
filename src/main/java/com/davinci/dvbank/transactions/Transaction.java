package com.davinci.dvbank.transactions;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

    @Id
    public String id;

    public String source;
    public String timeStamp;
    public String amount;
    public String type;

    public Transaction() {}

    public Transaction(String source, String amount, String type) {
        this.id = new ObjectId().toString();
        this.source = source;

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        this.timeStamp = dateFormat.format(date);

        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", amount='" + amount + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
