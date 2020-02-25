package com.davinci.dvbank.customers;

import org.springframework.data.annotation.Id;


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

    public Customer() {}

    public Customer(String firstName, String lastName, String province, String address, String postalCode,
                    String email, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
        this.address = address;
        this.postalCode = postalCode;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
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
                '}';
    }
}