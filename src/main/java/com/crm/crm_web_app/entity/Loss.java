package com.crm.crm_web_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Loss {
    @Id
    private Long id;

    private BigDecimal amount;
    private LocalDate date;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setAmount(BigDecimal loss) {
        this.amount = loss;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalDate getDate() {
        return date;
    }

}
