package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.entity.SalesForecast;
import com.crm.crm_web_app.service.SalesForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-forecasts")
public class SalesForecastController {

    @Autowired
    private SalesForecastService salesForecastService;

    @PostMapping
    public SalesForecast createSalesForecast(@RequestBody SalesForecast salesForecast) {
        return salesForecastService.createSalesForecast(salesForecast);
    }

    @GetMapping
    public List<SalesForecast> getAllSalesForecasts() {
        return salesForecastService.getAllSalesForecasts();
    }

    @GetMapping("/{id}")
    public SalesForecast getSalesForecastById(@PathVariable Long id) {
        return salesForecastService.getSalesForecastById(id);
    }
}
