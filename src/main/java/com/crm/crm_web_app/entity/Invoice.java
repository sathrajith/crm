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

    // The 'customer' field represents the owner side of the relationship
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Default constructor
    public Invoice() {}

    // Constructor with fields
    public Invoice(Double amount, String productDescription, LocalDateTime issueDate, LocalDateTime dueDate, InvoiceStatus status, Customer customer) {
        this.amount = amount;
        this.productDescription = productDescription;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
        this.customer = customer;
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
}
