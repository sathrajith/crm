package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.entity.Customer;
import com.crm.crm_web_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Create or update a customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get active customers
    @GetMapping("/active")
    public List<Customer> getActiveCustomers() {
        return customerService.getActiveCustomers();
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // Get customers by sales representative
    @GetMapping("/sales-rep/{salesRepId}")
    public List<Customer> getCustomersBySalesRepresentative(@PathVariable Long salesRepId) {
        return customerService.getCustomersBySalesRepresentative(salesRepId);
    }

    // Delete a customer by ID
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
//        if (updatedCustomer != null) {
//            return ResponseEntity.ok(updatedCustomer);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
        return ResponseEntity.ok(updatedCustomer);
    }
    @PostMapping("/{customerId}/assign-segment/{segmentId}")
    public Customer assignCustomerToSegment(@PathVariable Long customerId, @PathVariable Long segmentId) {
        return customerService.categorizeCustomerToSegment(customerId, segmentId);
    }

    // Endpoint to get customers by segment
    @GetMapping("/segment/{segmentId}")
    public List<Customer> getCustomersBySegment(@PathVariable Long segmentId) {
        return customerService.getCustomersBySegment(segmentId);
    }
}
