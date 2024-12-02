package com.crm.crm_web_app.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sales_activity")
public class SalesActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String activityType; // e.g., call, email, meeting
    private String description;
    private Date activityDate;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id", nullable = false)
    private SalesRepresentative salesRepresentative;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public SalesActivity() {}

    // Getters and Setters
}
