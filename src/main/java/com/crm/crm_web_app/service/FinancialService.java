package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.Invoice;
import com.crm.crm_web_app.repository.ExpenseRepository;
import com.crm.crm_web_app.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FinancialService {

    private final InvoiceRepository invoiceRepository;
    private final ExpenseRepository expenseRepository;

    public FinancialService(InvoiceRepository invoiceRepository, ExpenseRepository expenseRepository) {
        this.invoiceRepository = invoiceRepository;
        this.expenseRepository = expenseRepository;
    }

    public BigDecimal calculateTotalRevenue() {
        return invoiceRepository.calculateTotalRevenue();
    }
    public BigDecimal calculateTotalExpense() {
        return expenseRepository.calculateTotalExpense();
    }
    public BigDecimal calculateNetProfit() {
        BigDecimal revenue = calculateTotalRevenue();
        BigDecimal expenses = calculateTotalExpense();
        if(revenue == null) revenue = BigDecimal.ZERO;
        if(expenses == null) expenses = BigDecimal.ZERO;

        return revenue.subtract(expenses);
    }

}
