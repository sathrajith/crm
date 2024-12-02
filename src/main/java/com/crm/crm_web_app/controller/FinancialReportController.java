package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.entity.DailyFinancialRecord;
import com.crm.crm_web_app.service.EmailService;
import com.crm.crm_web_app.service.FinancialReportService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class FinancialReportController {
    @Autowired
    private FinancialReportService reportService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/monthly")
    public String generateMonthlyReport(@RequestParam int year, @RequestParam int month, @RequestParam String email) {
        List<DailyFinancialRecord> records = reportService.getMonthlyRecords(year, month);

        BigDecimal totalProfit = reportService.calculateTotalProfit(records);
        BigDecimal totalInvestment = reportService.calculateTotalInvestment(records);
        BigDecimal totalLoss = reportService.calculateTotalLoss(records);

        StringBuilder report = new StringBuilder();
        report.append("Monthly Financial Report");
        report.append("Total Profit:  ").append(totalProfit).append(" ");
        report.append("Total Investment:  ").append(totalInvestment).append(" ");
        report.append("Total Loss:  ").append(totalLoss).append(" ");
        report.append("Daily Records");
        for (DailyFinancialRecord record : records) {
            report.append("Date:  ").append(record.getDate())
                    .append(", Profit:  ").append(record.getProfit())
                    .append(", Investment:  ").append(record.getInvestment())
                    .append(", Loss:  ").append(record.getLoss())
                    .append(" ");
        }

        try {
            emailService.sendReport(email, "Monthly Financial Report", report.toString());
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send email.";
        }

        return "Monthly report sent to " + email;
    }
}
