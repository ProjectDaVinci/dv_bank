package com.davinci.dvbank.transactions;

import org.springframework.data.annotation.Id;

public class Transaction {

    @Id
    public String id;

    public String source;
    public String timeStamp;
    public String amount;
    public String type;

    public Transaction() {}

    public Transaction(String id, String source, String timeStamp, String amount, String type) {
        this.id = id;
        this.source = source;
        this.timeStamp = timeStamp;
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
