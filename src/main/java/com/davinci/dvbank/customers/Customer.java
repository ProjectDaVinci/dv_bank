package com.davinci.dvbank.customers;

import com.davinci.dvbank.accounts.Account;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    @Id
    public String id;

    public String firstName;
    public String lastName;
    public String province;
    public String address;
    public String city;
    public String postalCode;
    public String email;
    public String password;
    public String phoneNumber;
    public List<Account> accounts;

    public Customer() {}

    public Customer(String firstName, String lastName, String province, String address, String city, String postalCode,
                    String email, String password, String phoneNumber) {
        this.id = new ObjectId().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.accounts = new ArrayList<>();

        //Add 3 blank accounts
        this.accounts.add(new Account("chequing"));
        this.accounts.add(new Account("savings"));
        this.accounts.add(new Account("credit"));
    }

    public Customer(String firstName, String lastName, String province, String address, String city, String postalCode,
                    String email, String password, String phoneNumber, List<Account> accounts) {
        this.id = new ObjectId().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.accounts = accounts;
    }

    //Return the list of accounts
    public List<Account> getAccounts() {
        return accounts;
    }

    //Return a specific account by id
    public Account getAccount(String accountID){
        for (Account account : accounts) {
            if (account.id.equals(accountID)) {
                return account;
            }
        }

        return null;
    }

    //Return a specific account by type
    public Account getAccountByType(String type){
        for (Account account : accounts) {
            if (account.type.equals(type)) {
                return account;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", province='" + province + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}