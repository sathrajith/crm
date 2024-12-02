package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.entity.SalesActivity;
import com.crm.crm_web_app.service.SalesActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-activities")
public class SalesActivityController {

    @Autowired
    private SalesActivityService salesActivityService;

    @PostMapping
    public SalesActivity createSalesActivity(@RequestBody SalesActivity salesActivity) {
        return salesActivityService.createSalesActivity(salesActivity);
    }

    @GetMapping
    public List<SalesActivity> getAllSalesActivities() {
        return salesActivityService.getAllSalesActivities();
    }

    @GetMapping("/{id}")
    public SalesActivity getSalesActivityById(@PathVariable Long id) {
        return salesActivityService.getSalesActivityById(id);
    }
}
