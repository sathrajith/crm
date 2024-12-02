package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.DailyFinancialRecord;
import com.crm.crm_web_app.repository.FinancialRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class FinancialReportService {
    @Autowired

    private FinancialRecordRepository repository;

    public List<DailyFinancialRecord> getMonthlyRecords(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        return repository.findByDateBetween(startDate, endDate);
    }
    public BigDecimal calculateTotalProfit(List<DailyFinancialRecord> records) {
        return records.stream()
                .map(DailyFinancialRecord::getProfit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public BigDecimal calculateTotalInvestment(List<DailyFinancialRecord> records) {
        return records.stream()
                .map(DailyFinancialRecord::getInvestment)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public BigDecimal calculateTotalLoss(List<DailyFinancialRecord> records) {
        return records.stream()
                .map(DailyFinancialRecord::getLoss)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
