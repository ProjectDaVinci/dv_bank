package com.davinci.dvbank.accounts;

import com.davinci.dvbank.transactions.Transaction;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    public Account(String type) {
        this.id = new ObjectId().toString();
        this.number = generateAccountNum();
        this.type = type;
        this.balance = "0";

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        this.creationDate = dateFormat.format(date);

        this.status = "open";
        this.creditLimit = "0";
        this.transactions = new ArrayList<>();
    }

    public Account(String type, String balance, String creationDate, String status, String creditLimit, List<Transaction> transactions) {
        this.id = new ObjectId().toString();
        this.number = generateAccountNum();
        this.type = type;
        this.balance = balance;
        this.creationDate = creationDate;
        this.status = status;
        this.creditLimit = creditLimit;
        this.transactions = transactions;
    }

    public Account(String type, String balance, String creationDate, String status, List<Transaction> transactions) {
        new Account(type, balance, creationDate, status, "0", transactions);
    }

    //Return a list of all transactions
    public List<Transaction> getTransactions() {
        return transactions;
    }

    //Get a specific transaction
    public Transaction getTransaction(String transID){
        for (Transaction trans : transactions) {
            if (trans.id.equals(transID)) {
                return trans;
            }
        }

        return null;
    }

    //Generate a random account number in a range
    private String generateAccountNum(){
        Random rand = new Random();
        int max = 5000;
        int min = 3000;
        return String.valueOf(rand.nextInt((max - min) + 1) + min);
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