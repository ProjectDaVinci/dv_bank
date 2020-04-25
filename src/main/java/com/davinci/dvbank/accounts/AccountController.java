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
    @RequestMapping("/{customerID}/{accountID}/get")
    public Account getAccount(@PathVariable String customerID, @PathVariable String accountID){
        return repository.findById(customerID).get().getAccount(accountID);
    }

    //Create a new account for a particular customer
    @RequestMapping("/{customerID}/add")
    public String addAccount(@PathVariable String customerID, @RequestBody Account newAccount){
      
        try{
            Customer customerToChange = repository.findById(customerID).orElse(null);

            if (customerToChange == null){
                throw new IllegalArgumentException("Null customer");
            }

            customerToChange.accounts.add(newAccount);
            repository.save(customerToChange);
            return "Success";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    //Update an existing account
    @RequestMapping("/{customerID}/{accountID}/update")
    public String updateAccount(@PathVariable String customerID, @PathVariable String accountID, @RequestBody Account newAccount){

        try{
            //Get the customer that needs updating
            Customer customerToChange = repository.findById(customerID).orElse(null);

            //Check if the record exists
            if (customerToChange == null){
                throw new IllegalArgumentException("Null account");
            }

            //Update the allowed fields with the new data
            customerToChange.getAccount(accountID).type = newAccount.type;
            customerToChange.getAccount(accountID).balance = newAccount.balance;
            customerToChange.getAccount(accountID).status = newAccount.status;

            //Save the data changes
            repository.save(customerToChange);
            return "Success";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    //Delete a specific account of a particular customer
    @RequestMapping("/{customerID}/{accountID}/delete")
    public String deleteAccount(@PathVariable String customerID, @PathVariable String accountID){

        try{
            //Get the customer that needs updating
            Customer customerToChange = repository.findById(customerID).orElse(null);

            //Check if the record exists
            if (customerToChange == null){
                throw new IllegalArgumentException("Null account");
            }

            //Erase the specified account by setting it to null
            customerToChange.setAccount(accountID, null);
            return "Success";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    //Delete all accounts for a particular customer
    @RequestMapping("/{customerID}/deleteAllAccounts")
    public String deleteAccounts(@PathVariable String customerID){

        try{
            //Get the customer that needs updating
            Customer customerToChange = repository.findById(customerID).orElse(null);

            //Check if the record exists
            if (customerToChange == null){
                throw new IllegalArgumentException("Null account");
            }

            customerToChange.setAccounts(null);
            return "Success";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }
}
