package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.Investment;
import com.crm.crm_web_app.entity.Profit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProfitRepository extends JpaRepository<Profit, Long> {
    List<Profit> findByDateBetween(LocalDate start, LocalDate end);
    Optional<Profit> findByInvestment(Investment investment);
}
