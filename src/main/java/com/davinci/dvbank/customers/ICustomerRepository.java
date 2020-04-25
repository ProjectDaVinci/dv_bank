package com.davinci.dvbank.customers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ICustomerRepository extends MongoRepository<Customer, String> {

    public List<Customer> findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);
    public Optional<Customer> findById(String id);
    @Query("{email:'?0'}")
    public Customer findByEmail(String email);
    //login


}
