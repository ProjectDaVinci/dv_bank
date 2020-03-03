package com.davinci.dvbank.accounts;

import com.davinci.dvbank.customers.Customer;
import com.davinci.dvbank.customers.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private ICustomerRepository repository;

    @RequestMapping("/{customerID}/getAccounts")
    public List<Account> getAccounts(@PathVariable String customerID){
        return repository.findById(customerID).get().accounts;
    }

    @RequestMapping("/{customerID}/addAccount")
    public String addAccount(@PathVariable String customerID, @RequestBody Account newAccount){
        Customer customerToChange = repository.findById(customerID).orElse(null);

        if (customerToChange == null){
            throw new IllegalArgumentException("Null customer");
        }

        customerToChange.accounts.add(newAccount);
        repository.save(customerToChange);
        return "Success";
    }
}
