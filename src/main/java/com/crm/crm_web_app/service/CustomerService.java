package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.Customer;
import com.crm.crm_web_app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Save or update a customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get active customers (assuming you have a field 'active' in Customer entity)
    public List<Customer> getActiveCustomers() {
        return customerRepository.findByActive(true); // You'll need to implement this in the repo
    }

    // Get customer by ID
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    // Get customers by sales representative ID
    public List<Customer> getCustomersBySalesRepresentative(Long salesRepId) {
        return customerRepository.findBySalesRepId(salesRepId); // Implement this in the repo
    }

    // Delete a customer by ID
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    public Customer updateCustomer(Long id, Customer customerDetails) {
        // Fetch the customer by ID
        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);

        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();

            // Update the customer fields with the new details
            existingCustomer.setEmail(customerDetails.getEmail());
            existingCustomer.setName(customerDetails.getName());
            existingCustomer.setPhone(customerDetails.getPhone());
            existingCustomer.setAddress(customerDetails.getAddress());
            existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
            existingCustomer.setSegment(customerDetails.getSegment());
            existingCustomer.setFirstName(customerDetails.getFirstName());
            existingCustomer.setLastName(customerDetails.getLastName());
            existingCustomer.setActive(customerDetails.isActive());
            existingCustomer.setSalesRepId(customerDetails.getSalesRepId());
            // Add any other fields you want to update

            // Save the updated customer to the database
            return customerRepository.save(existingCustomer);
        } else {
            // If customer doesn't exist, return null or throw exception
            return null;  // Or throw a custom exception, e.g., CustomerNotFoundException
        }
    }

}
