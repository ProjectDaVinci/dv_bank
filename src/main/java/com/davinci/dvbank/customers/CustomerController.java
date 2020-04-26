package com.davinci.dvbank.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    //Get a singleton of the database
    @Autowired
    private ICustomerRepository repository;

    //Return a list of customers matching a first name input
    @GetMapping("/getByFirstName/{firstName}")
    public List<Customer> getByFirstName(@PathVariable String firstName){
        return repository.findByFirstName(firstName);
    }

    //Return a list of customers matching a last name input
    @GetMapping("/getByLastName/{lastName}")
    public List<Customer> getByLastName(@PathVariable String lastName){
        return repository.findByLastName(lastName);
    }

    //Return a single customer matching an id input
    @GetMapping("/getByID/{id}")
    public Customer getByID(@PathVariable String id){
        return repository.findById(id).orElse(null);
    }

    //Add a new customer record
    @RequestMapping("/add")
    public String addCustomer(@RequestBody Customer newCustomer){

        //Call the appropriate constructor with the new customer values
        Customer custToAdd = new Customer(newCustomer.firstName, newCustomer.lastName, newCustomer.province, newCustomer.address,
                newCustomer.city, newCustomer.postalCode, newCustomer.email, newCustomer.password, newCustomer.phoneNumber);

        //Check if the new customer's email already exists in the database
        for (Customer customer : repository.findAll()) {
            if (custToAdd.email.equals(customer.email)){
                return "Email address already exists in database";
            }
        }

        //Save the new record if it passed the requirement
        repository.save(custToAdd);
        return "Success";
    }

    //Update an existing customer record
    @RequestMapping("/{customerID}/update")
    public String updateCustomer(@PathVariable String customerID, @RequestBody Customer newCustomer){

        try{
            //Get the customer that needs updating
            Customer customerToChange = repository.findById(customerID).orElse(null);

            //Check if the record exists
            if (customerToChange == null){
                throw new IllegalArgumentException("Null account");
            }

            //Update the allowed fields with the new data
            customerToChange.province = newCustomer.province;
            customerToChange.address = newCustomer.address;
            customerToChange.postalCode = newCustomer.postalCode;
            customerToChange.province = newCustomer.province;
            customerToChange.city = newCustomer.city;
            customerToChange.email = newCustomer.email;
            customerToChange.phoneNumber = newCustomer.phoneNumber;

            //Save the data changes
            repository.save(customerToChange);
            return "Success";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    //Delete a customer record
    @RequestMapping("/{customerID}/delete")
    public String deleteCustomer(@PathVariable String customerID){

        try{
            //Get the customer that needs updating
            Customer customerToChange = repository.findById(customerID).orElse(null);

            //Check if the record exists
            if (customerToChange == null){
                throw new IllegalArgumentException("Null account");
            }

            repository.deleteById(customerID);
            return "Success";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

//    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
//    public Customer login(@RequestBody Customer loginUser){
//        if(repository.findByEmail(loginUser.email).password.equals(loginUser.password))
//            return repository.findByEmail(loginUser.email);
//        else
//            return null;
//    }

//    @PostMapping(value = "/addCustomer", consumes = "application/json", produces = "application/json")
//    public Customer addCustomer(@RequestBody Customer newCustomer){
//        return repository.save(newCustomer);
//    }
//
//    @PutMapping(value="/{id}")
//    public Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable String id){
//        return repository.findById(id).map(customer-> newCustomer).orElseGet(()->repository.save(newCustomer));
//    }
}
