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

    //Get a specific account by id from a particular customer
    @RequestMapping("/{customerID}/{accountID}/getByID")
    public Account getAccountByID(@PathVariable String customerID, @PathVariable String accountID){
        return repository.findById(customerID).get().getAccount(accountID);
    }

    //Get a specific account by type from a particular customer
    @RequestMapping("/{customerID}/{accountType}/getByType")
    public Account getAccountByType(@PathVariable String customerID, @PathVariable String accountType){
        return repository.findById(customerID).get().getAccountByType(accountType);
    }

    //Create a new, blank account for a particular customer
    @RequestMapping("/{customerID}/{accountType}/add")
    public String addAccount(@PathVariable String customerID, @PathVariable String accountType){
      
        try{
            Customer customerToChange = repository.findById(customerID).orElse(null);
            Account accountToAdd = new Account(accountType);

            if (customerToChange == null){
                throw new IllegalArgumentException("Null customer");
            }

            customerToChange.accounts.add(accountToAdd);
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

            //Remove the specified account from the list of accounts
            customerToChange.getAccounts().remove(customerToChange.getAccount(accountID));
            repository.save(customerToChange);
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

            //Empty out the array list
            customerToChange.getAccounts().clear();
            repository.save(customerToChange);
            return "Success";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }
}
