package com.crm.crm_web_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class DailyFinancialRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private BigDecimal profit;

    private BigDecimal investment;

    private BigDecimal loss;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public BigDecimal getProfit() {
        return profit;
    }
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
    public BigDecimal getInvestment() {
        return investment;
    }
    public void setInvestment(BigDecimal investment) {
        this.investment = investment;
    }
    public BigDecimal getLoss() {
        return loss;
    }
    public void setLoss(BigDecimal loss) {
        this.loss = loss;
    }

}