package com.davinci.dvbank.customers;

class CustomerNotFoundException extends RuntimeException {

    CustomerNotFoundException(String id) {
        super("Could not find customer " + id);
    }
}
