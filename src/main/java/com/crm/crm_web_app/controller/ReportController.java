package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/monthly")
    public ResponseEntity<Map<String, BigDecimal>> getMonthlyReport(
            @RequestParam int month, @RequestParam int year) {
        Map<String, BigDecimal> report = reportService.generateMonthlyReport(month, year);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/yearly")
    public ResponseEntity<Map<String, BigDecimal>> getYearlyReport(
            @RequestParam int year) {
        Map<String, BigDecimal> report = reportService.generateYearlyReport(year);
        return ResponseEntity.ok(report);
    }
}

