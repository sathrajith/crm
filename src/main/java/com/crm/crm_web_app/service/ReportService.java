package com.crm.crm_web_app.service;

import com.crm.crm_web_app.dto.Report;
import com.crm.crm_web_app.dto.ReportRequest;
import com.crm.crm_web_app.repository.CustomerRepository;
import com.crm.crm_web_app.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Report generateReport(ReportRequest reportRequest) {

        // If you're working with LocalDate, you should convert to Date, or use LocalDate for dates.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Assuming ReportRequest contains LocalDate fields (startDate and endDate), so you need to convert them to Date
        Date startDate = convertLocalDateToDate(reportRequest.getStartDate());
        Date endDate = convertLocalDateToDate(reportRequest.getEndDate());

        // Now create the report with formatted start and end dates
        Report report = new Report();
        report.setStartDate(sdf.format(startDate));  // If you want to store the date as String
        report.setEndDate(sdf.format(endDate));      // If you want to store the date as String

        return report;
    }

    public Map<String, Object> generateReport(Date startDate, Date endDate) {
        Map<String, Object> report = new HashMap<>();
        report.put("totalCustomers", customerRepository.count());
        report.put("totalLeads", leadRepository.count());
        report.put("conversionRate", calculateConversionRate());
        report.put("dateRange", String.format("%tF to %tF", startDate, endDate));
        return report;
    }

    private double calculateConversionRate() {
        long totalLeads = leadRepository.count();
        long convertedLeads = leadRepository.countByStatus("Converted");
        return totalLeads == 0 ? 0 : (double) convertedLeads / totalLeads * 100;
    }

    // Helper method to convert LocalDate to Date
    private Date convertLocalDateToDate(java.time.LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);  // Convert LocalDate to Date (java.sql.Date)
    }
}
