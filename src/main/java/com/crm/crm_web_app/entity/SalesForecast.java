package com.crm.crm_web_app.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales_forecast")
public class SalesForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double forecastAmount;
    private Date forecastDate;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id", nullable = false)
    private SalesRepresentative salesRepresentative;

    public SalesForecast() {}

    // Getters and Setters
}
