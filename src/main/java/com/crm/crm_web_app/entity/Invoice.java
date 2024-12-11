package com.crm.crm_web_app.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    private String productDescription;

    @Column(nullable = false)
    private LocalDateTime issueDate;

    private LocalDateTime dueDate;

    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false)
    private Double discount = 0.0; // Default no discount

    // Default constructor
    public Invoice() {}

    // Constructor with fields
    public Invoice(Double amount, String productDescription, LocalDateTime issueDate, LocalDateTime dueDate, InvoiceStatus status, Customer customer, Double discount) {
        this.amount = amount;
        this.productDescription = productDescription;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
        this.customer = customer;
        this.discount = discount;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        if (paymentDate != null && paymentDate.isBefore(issueDate)) {
            throw new IllegalArgumentException("Payment date cannot be before issue date");
        }
        this.paymentDate = paymentDate;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    // Calculate final amount after applying discount
    public Double getFinalAmount() {
        return amount - (amount * (discount / 100));
    }

    // Method to update the invoice status based on the payment date
    public void updateInvoiceStatus() {
        if (paymentDate != null) {
            status = InvoiceStatus.PAID;
        } else if (LocalDateTime.now().isAfter(dueDate) && status != InvoiceStatus.PAID) {
            status = InvoiceStatus.OVERDUE;
        } else {
            status = InvoiceStatus.PENDING;
        }
    }
}
