package com.crm.crm_web_app.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "quote")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateIssued;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id", nullable = false)
    private SalesRepresentative salesRepresentative;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "quote")
    private List<QuoteItem> quoteItems;

    public Quote() {}

    // Getters and Setters
}
