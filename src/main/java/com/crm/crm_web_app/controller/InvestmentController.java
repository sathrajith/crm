package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.entity.Investment;
import com.crm.crm_web_app.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    private final InvestmentService investmentService;

    @Autowired
    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    // Create or update investment
    @PostMapping
    public ResponseEntity<Investment> createInvestment(@RequestBody Investment investment) {
        Investment savedInvestment = investmentService.saveInvestment(investment);
        return ResponseEntity.ok(savedInvestment);
    }

    // Get investment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Investment> getInvestmentById(@PathVariable Long id) {
        Optional<Investment> investment = investmentService.getInvestmentById(id);
        return investment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all investments
    @GetMapping
    public List<Investment> getAllInvestments() {
        return investmentService.getAllInvestments();
    }

    // Delete investment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
        investmentService.deleteInvestmentById(id);
        return ResponseEntity.noContent().build();
    }

    // Get ROI for an investment
    @GetMapping("/{id}/roi")
    public ResponseEntity<BigDecimal> getROI(@PathVariable Long id) {
        BigDecimal roi = investmentService.calculateROI(id);
        return ResponseEntity.ok(roi);
    }
}
