package com.davinci.dvbank.accounts;

import com.davinci.dvbank.transactions.Transaction;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Account {

    @Id
    public String id;

    public String number;
    public String type;
    public String balance;
    public String creationDate;
    public String status;
    public String creditLimit;
    public List<Transaction> transactions;

    public Account() {}

    public Account(String id, String number, String type, String balance, String creationDate, String status, String creditLimit, List<Transaction> transactions) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.balance = balance;
        this.creationDate = creationDate;
        this.status = status;
        this.creditLimit = creditLimit;
        this.transactions = transactions;
    }

    public Account(String id, String number, String type, String balance, String creationDate, String status, List<Transaction> transactions) {
        new Account(id, number, type, balance, creationDate, status, "0", transactions);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", balance='" + balance + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", status='" + status + '\'' +
                ", creditLimit='" + creditLimit + '\'' +
                ", transactions=" + transactions +
                '}';
    }
}
