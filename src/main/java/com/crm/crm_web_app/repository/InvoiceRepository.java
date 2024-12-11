package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.Invoice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
	List<Invoice> findByCustomerId(Long customerId);
	
	List<Invoice> findByStatus(String status);

}

