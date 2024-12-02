package com.crm.crm_web_app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quote_item")
public class QuoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double unitPrice;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quote;

    public QuoteItem() {}

    // Getters and Setters
}
