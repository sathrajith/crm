package com.crm.crm_web_app.entity;

import jakarta.persistence.*;

@Entity
public class LeadStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;    // Lead source (e.g., Website, Social Media, etc.)
    private Long leadCount;   // Number of leads from this source

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getLeadCount() {
        return leadCount;
    }

    public void setLeadCount(Long leadCount) {
        this.leadCount = leadCount;
    }
}
