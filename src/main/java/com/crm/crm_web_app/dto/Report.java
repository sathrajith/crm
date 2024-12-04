package com.crm.crm_web_app.dto;

import java.math.BigDecimal;

public class Report {
    private String reportTitle;
    private Double totalProfit;
    private Double totalLoss;
    private Double totalInvestment;
    private String startDate;
    private String endDate;
    private Long totalCustomers;
    private Long totalLeads;
    private double conversionRate;
    private BigDecimal totalRevenue;
    private BigDecimal totalExpenses;
    private BigDecimal netProfit;


    // Getters and Setters
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Double getTotalLoss() {
        return totalLoss;
    }

    public void setTotalLoss(Double totalLoss) {
        this.totalLoss = totalLoss;
    }

    public Double getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(Double totalInvestment) {
        this.totalInvestment = totalInvestment;
    }
    public Long getTotalCustomers() {
        return totalCustomers;
    }
    public void setTotalCustomers(Long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }
    public Long getTotalLeads() {
        return totalLeads;
    }
    public void setTotalLeads(Long totalLeads) {
        this.totalLeads = totalLeads;
    }
    public double getConversionRate() {
        return conversionRate;
    }
    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }
    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }
    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
    public BigDecimal getNetProfit() {
        return netProfit;
    }
    public void setNetProfit(BigDecimal netProfit) {
        this.netProfit = netProfit;
    }




}
