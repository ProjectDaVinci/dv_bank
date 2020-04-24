package com.davinci.dvbank.accounts;

import com.davinci.dvbank.customers.Customer;
import com.davinci.dvbank.customers.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    //Get a singleton of the database
    @Autowired
    private ICustomerRepository repository;

    //Get a list of accounts for a particular customer
    @RequestMapping("/{customerID}/getAccounts")
    public List<Account> getAccounts(@PathVariable String customerID){
        return repository.findById(customerID).get().accounts;
    }

    //Get a specific account from a particular customer


    //Create a new account for a particular customer
    @RequestMapping("/{customerID}/addAccount")
    public Customer addAccount(@PathVariable String customerID, @RequestBody Account newAccount){
        Customer customerToChange = repository.findById(customerID).orElse(null);

        if (customerToChange == null){
            throw new IllegalArgumentException("Null customer");
        }
        customerToChange.accounts.add(newAccount);
        repository.save(customerToChange);
        return repository.findById(customerID).get();
    }
}
