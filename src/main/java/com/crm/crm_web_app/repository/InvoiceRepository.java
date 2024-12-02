package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}

