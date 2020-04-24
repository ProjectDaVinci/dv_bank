package com.davinci.dvbank.customers;

import com.davinci.dvbank.accounts.Account;
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
    public String postalCode;
    public String email;
    public String password;
    public String phoneNumber;
    public List<Account> accounts = new ArrayList<>();

    public Customer() {}

    public Customer(String id, String firstName, String lastName, String province, String address, String postalCode,
                    String email, String password, String phoneNumber, List<Account> accounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
        this.address = address;
        this.postalCode = postalCode;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", province='" + province + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}