package com.crm.crm_web_app.service;

import com.crm.crm_web_app.dto.Report;
import com.crm.crm_web_app.dto.ReportRequest;
import com.crm.crm_web_app.repository.CustomerRepository;
import com.crm.crm_web_app.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private FinancialService financialService;

    @Autowired
    private JavaMailSender mailSender;

    // Generate Report Based on Request
    public Report generateReport(ReportRequest reportRequest) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = convertLocalDateToDate(reportRequest.getStartDate());
        Date endDate = convertLocalDateToDate(reportRequest.getEndDate());

        Report report = new Report();
        report.setStartDate(sdf.format(startDate));
        report.setEndDate(sdf.format(endDate));
        report.setTotalCustomers(customerRepository.count());
        report.setTotalLeads(leadRepository.count());
        report.setConversionRate(calculateConversionRate());
        report.setTotalRevenue(financialService.calculateTotalRevenue());
        report.setTotalExpenses(financialService.calculateTotalExpense());
        report.setNetProfit(financialService.calculateNetProfit());

        return report;
    }

    // Generate Financial Report Between Dates
    public Map<String, Object> generateReport(Date startDate, Date endDate) {
        Map<String, Object> report = new HashMap<>();
        report.put("totalCustomers", customerRepository.count());
        report.put("totalLeads", leadRepository.count());
        report.put("conversionRate", calculateConversionRate());
        report.put("totalRevenue", financialService.calculateTotalRevenue());
        report.put("totalExpenses", financialService.calculateTotalExpense());
        report.put("netProfit", financialService.calculateNetProfit());
        report.put("dateRange", String.format("%tF to %tF", startDate, endDate));
        return report;
    }

    // Scheduled Email for Monthly Financial Report
    @Scheduled(cron = "0 0 1 * * ?") // Run on the 1st day of every month
    public void sendMonthlyFinancialReport() {
        BigDecimal revenue = financialService.calculateTotalRevenue();
        BigDecimal expenses = financialService.calculateTotalExpense();
        BigDecimal profit = financialService.calculateNetProfit();

        String emailContent = String.format(
                "Monthly Financial Report:\n\nRevenue: %s\nExpenses: %s\nNet Profit: %s",
                revenue, expenses, profit
        );

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("admin@example.com");
        message.setSubject("Monthly Financial Report");
        message.setText(emailContent);

        mailSender.send(message);
    }

    // Helper Methods
    private double calculateConversionRate() {
        long totalLeads = leadRepository.count();
        long convertedLeads = leadRepository.countByStatus("Converted");
        return totalLeads == 0 ? 0 : (double) convertedLeads / totalLeads * 100;
    }

    private Date convertLocalDateToDate(java.time.LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }
}
