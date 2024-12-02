package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.Investment;
import com.crm.crm_web_app.entity.Loss;
import com.crm.crm_web_app.entity.Profit;
import com.crm.crm_web_app.repository.InvestmentRepository;
import com.crm.crm_web_app.repository.LossRepository;
import com.crm.crm_web_app.repository.ProfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private InvestmentRepository investmentRepository;
    @Autowired
    private ProfitRepository profitRepository;
    @Autowired
    private LossRepository lossRepository;

    public BigDecimal calculateProfitLossForPeriod(LocalDate startDate, LocalDate endDate) {
        // Get all investments, profits, and losses for the period
        List<Investment> investments = investmentRepository.findByDateBetween(startDate, endDate);
        List<Profit> profits = profitRepository.findByDateBetween(startDate, endDate);
        List<Loss> losses = lossRepository.findByDateBetween(startDate, endDate);

        BigDecimal totalInvestment = investments.stream()
                .map(Investment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalProfit = profits.stream()
                .map(Profit::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalLoss = losses.stream()
                .map(Loss::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calculate total profit/loss
        return totalProfit.subtract(totalLoss).subtract(totalInvestment);
    }

    public Map<String, BigDecimal> generateMonthlyReport(int month, int year) {
        // Start and end of the month
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        BigDecimal profitLoss = calculateProfitLossForPeriod(startDate, endDate);

        Map<String, BigDecimal> report = new HashMap<>();
        report.put("Profit/Loss for " + startDate.getMonth().toString() + " " + year, profitLoss);
        return report;
    }

    public Map<String, BigDecimal> generateYearlyReport(int year) {
        // Start and end of the year
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);

        BigDecimal profitLoss = calculateProfitLossForPeriod(startDate, endDate);

        Map<String, BigDecimal> report = new HashMap<>();
        report.put("Profit/Loss for " + year, profitLoss);
        return report;
    }
}

