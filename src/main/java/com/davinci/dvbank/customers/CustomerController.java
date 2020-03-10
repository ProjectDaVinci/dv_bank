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

    @RequestMapping("/getAllCustomers")
    List<Customer> all() {
        return repository.findAll();
    }


    @RequestMapping("/addCustomer")
    public String addCustomer(@RequestBody Customer newCustomer){

        repository.save(newCustomer);
        return "Success";
    }

    @RequestMapping("/getCustomerByID/{id}")
    Customer one(@PathVariable String id) {

        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }






}
