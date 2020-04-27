package com.davinci.dvbank.transactions;

import com.davinci.dvbank.customers.Customer;
import com.davinci.dvbank.customers.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    //Get a singleton of the database
    @Autowired
    private ICustomerRepository repository;

    //Get all transactions of a specific customer's specific account
    @RequestMapping("/{customerID}/{accountID}/getTransactions")
    public List<Transaction> getTransactions(@PathVariable String customerID, @PathVariable String accountID){
        return repository.findById(customerID).get().getAccount(accountID).getTransactions();
    }

    //Get a transaction of a specific customer's specific account
    @RequestMapping("/{customerID}/{accountID}/{transactionID}/get")
    public Transaction getTransaction(@PathVariable String customerID, @PathVariable String accountID, @PathVariable String transactionID){
        return repository.findById(customerID).get().getAccount(accountID).getTransaction(transactionID);
    }

    //Create a new transaction in a specific customer's specific account
    @RequestMapping("/{customerID}/{accountID}/add")
    public String addTransaction(@PathVariable String customerID, @PathVariable String accountID, @RequestBody Transaction newTransaction){

        try{
            Customer customerToChange = repository.findById(customerID).orElse(null);
            Transaction transToAdd = new Transaction(newTransaction.source, newTransaction.amount, newTransaction.type);

            if (customerToChange == null){
                throw new IllegalArgumentException("Customer does not exist");
            }

            if (customerToChange.getAccount(accountID) == null){
                throw new IllegalArgumentException("Account does not exist");
            }

            customerToChange.getAccount(accountID).getTransactions().add(transToAdd);
            repository.save(customerToChange);
            return "Success";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    //Update a transaction from a specific customer's specific account
    @RequestMapping("/{customerID}/{accountID}/{transactionID}/update")
    public String updateTransaction(@PathVariable String customerID, @PathVariable String accountID, @PathVariable String transactionID, @RequestBody Transaction newTransaction){

        try{
            //Get the customer that needs updating
            Customer customerToChange = repository.findById(customerID).orElse(null);

            //Check if the records exist
            if (customerToChange == null){
                throw new IllegalArgumentException("Customer does not exist");
            }

            if (customerToChange.getAccount(accountID) == null){
                throw new IllegalArgumentException("Account does not exist");
            }

            if (customerToChange.getAccount(accountID).getTransaction(transactionID) == null){
                throw new IllegalArgumentException("Transaction does not exist");
            }

            //Update the allowed fields with the new data
            customerToChange.getAccount(accountID).getTransaction(transactionID).source = newTransaction.source;
            customerToChange.getAccount(accountID).getTransaction(transactionID).timeStamp = newTransaction.timeStamp;
            customerToChange.getAccount(accountID).getTransaction(transactionID).amount = newTransaction.amount;
            customerToChange.getAccount(accountID).getTransaction(transactionID).type = newTransaction.type;

            //Save the data changes
            repository.save(customerToChange);
            return "Success";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    //Delete a transaction from a specific customer's specific account
    @RequestMapping("/{customerID}/{accountID}/{transactionID}/delete")
    public String deleteAccount(@PathVariable String customerID, @PathVariable String accountID, @PathVariable String transactionID){

        try{
            //Get the customer that needs updating
            Customer customerToChange = repository.findById(customerID).orElse(null);

            //Check if the records exist
            if (customerToChange == null){
                throw new IllegalArgumentException("Customer does not exist");
            }

            if (customerToChange.getAccount(accountID) == null){
                throw new IllegalArgumentException("Account does not exist");
            }

            if (customerToChange.getAccount(accountID).getTransaction(transactionID) == null){
                throw new IllegalArgumentException("Transaction does not exist");
            }

            //Remove the specified transaction
            customerToChange.getAccount(accountID).getTransactions().remove(customerToChange.getAccount(accountID).getTransaction(transactionID));
            repository.save(customerToChange);
            return "Success";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    //Delete all transactions from a specific customer's specific account
    @RequestMapping("/{customerID}/{accountID}/deleteAllTransactions")
    public String deleteAccounts(@PathVariable String customerID, @PathVariable String accountID){

        try{
            //Get the customer that needs updating
            Customer customerToChange = repository.findById(customerID).orElse(null);

            //Check if the records exist
            if (customerToChange == null){
                throw new IllegalArgumentException("Customer does not exist");
            }

            if (customerToChange.getAccount(accountID) == null){
                throw new IllegalArgumentException("Account does not exist");
            }

            //Empty out the array list
            if (customerToChange.getAccount(accountID).getTransactions().size() > 0){
                customerToChange.getAccount(accountID).getTransactions().clear();
            }

            repository.save(customerToChange);
            return "Success";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }
}