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


        //repository.deleteAll();

        // save a couple of customers
//		repository.save(new Customer("Alice", "Smith"));
//		repository.save(new Customer("Bob", "Smith"));

//		// fetch all customers
//		System.out.println("Customers found with findAll():");
//		System.out.println("-------------------------------");
//		for (Customer customer : repository.findAll()) {
//			System.out.println(customer);
//		}
//		System.out.println();
//
//		// fetch an individual customer
//		System.out.println("Customer found with findByFirstName('Alice'):");
//		System.out.println("--------------------------------");
//		System.out.println(repository.findByFirstName("Alice"));
//
//		System.out.println("Customers found with findByLastName('Smith'):");
//		System.out.println("--------------------------------");
//		for (Customer customer : repository.findByLastName("Smith")) {
//			System.out.println(customer);
//		}
    }
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public Customer login(@RequestBody Customer loginUser){
         if(repository.findByEmail(loginUser.email).password.equals(loginUser.password))
            return repository.findByEmail(loginUser.email);
         else
            return null;
    }
    @PostMapping(value = "/addCustomer", consumes = "application/json", produces = "application/json")
    public Customer addCustomer(@RequestBody Customer newCustomer){
        return repository.save(newCustomer);
    }
}
