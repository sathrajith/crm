package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
