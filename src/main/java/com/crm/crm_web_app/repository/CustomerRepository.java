package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Assuming you are using 'active' field in Customer entity and 'salesRepresentative' as a field in Customer entity
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Custom query to find customers who are active
    List<Customer> findByActive(Boolean active);

    // Custom query to find customers by sales representative ID
    List<Customer> findBySalesRepId(Long salesRepId);
}
