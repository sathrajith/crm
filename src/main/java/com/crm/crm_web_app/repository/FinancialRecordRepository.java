package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.DailyFinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialRecordRepository extends JpaRepository<DailyFinancialRecord, Long> {
    List<DailyFinancialRecord> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
