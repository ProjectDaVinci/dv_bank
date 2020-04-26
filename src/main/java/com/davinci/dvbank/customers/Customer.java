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
    public List<Account> accounts = new ArrayList<>();

    public Customer() {}

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

    //Return a specific account
    public Account getAccount(String accountID){
        for (Account account : accounts) {
            if (account.id.equals(accountID)) {
                return account;
            }
        }

        return null;
    }

    //Apply a change to all accounts
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    //Apply a change to a specific account
    public void setAccount(String accountID, Account newAccount){
        for (Account account : accounts) {
            if (account.id.equals(accountID)) {
                account = newAccount;
                break;
            }
        }
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