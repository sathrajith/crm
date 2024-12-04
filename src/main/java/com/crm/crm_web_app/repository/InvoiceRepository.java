package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT SUM(i.amount) FROM Invoice i WHERE i.status = 'PAID'")
    BigDecimal calculateTotalRevenue();
}

