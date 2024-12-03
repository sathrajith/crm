package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.dto.Report;
import com.crm.crm_web_app.dto.ReportRequest;
import com.crm.crm_web_app.service.ReportExportService;
import com.crm.crm_web_app.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/report")  // Base path for all report-related endpoints
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportExportService reportExportService;

    // Generate report (Get)
    @GetMapping
    public Map<String, Object> getReport(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        return reportService.generateReport(startDate, endDate);
    }

    // Download report as PDF (Get)
    @GetMapping("/pdf")
    public void downloadPdfReport(HttpServletResponse response,
                                  @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                  @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Map<String, Object> report = reportService.generateReport(startDate, endDate);
        reportExportService.exportToPdf(report, response);
    }

    // Download report as Excel (Get)
    @GetMapping("/excel")
    public void downloadExcelReport(HttpServletResponse response,
                                    @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                    @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Map<String, Object> report = reportService.generateReport(startDate, endDate);
        reportExportService.exportToExcel(report, response);
    }

    // Generate report (Post)
    @PostMapping
    public Report generateReport(@RequestBody ReportRequest request) {
        return reportService.generateReport(request);
    }
}
