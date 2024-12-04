package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.service.FinancialService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/api/financial")
public class FinancialController {

    private final FinancialService financialService;

    public FinancialController(FinancialService financialService) {
        this.financialService = financialService;
    }

    @GetMapping("/revenue")
    public ResponseEntity<BigDecimal> getTotalrevenue() {
        return ResponseEntity.ok(financialService.calculateTotalRevenue());
    }
    @GetMapping("/expenses")
    public ResponseEntity<BigDecimal> getTotalexpenses() {
        return ResponseEntity.ok(financialService.calculateTotalExpense());
    }
    @GetMapping("/profit")
    public ResponseEntity<BigDecimal> getNetProfit() {
        return ResponseEntity.ok(financialService.calculateNetProfit());
    }
}
