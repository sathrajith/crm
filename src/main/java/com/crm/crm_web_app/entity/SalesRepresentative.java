package com.crm.crm_web_app.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sales_representative")
public class SalesRepresentative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "salesRepresentative")
    private List<SalesActivity> activities;

    public SalesRepresentative() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

}
