package com.davinci.dvbank.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerRepository repository;

    @GetMapping("/getByFirstName/{firstName}")
    public List<Customer> getByFirstName(@PathVariable String firstName){
        return repository.findByFirstName(firstName);
    }

    @PostMapping(value = "/addCustomer", consumes = "application/json", produces = "application/json")
    public Customer addCustomer(@RequestBody Customer newCustomer){
        return repository.save(newCustomer);
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public Customer login(@RequestBody Customer loginUser){
        if(repository.findByEmail(loginUser.email).password.equals(loginUser.password))
            return repository.findByEmail(loginUser.email);
        else
            return null;
    }
    @PutMapping(value="/{id}")
    public Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable String id){
        return repository.findById(id).map(customer-> newCustomer).orElseGet(()->repository.save(newCustomer));
    }

}
