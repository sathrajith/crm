package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByDateBetween(LocalDate start, LocalDate end);
}
