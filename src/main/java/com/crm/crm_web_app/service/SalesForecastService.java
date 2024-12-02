package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.SalesForecast;
import com.crm.crm_web_app.repository.SalesForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesForecastService {

    @Autowired
    private SalesForecastRepository salesForecastRepository;

    public SalesForecast createSalesForecast(SalesForecast salesForecast) {
        return salesForecastRepository.save(salesForecast);
    }

    public List<SalesForecast> getAllSalesForecasts() {
        return salesForecastRepository.findAll();
    }

    public SalesForecast getSalesForecastById(Long id) {
        return salesForecastRepository.findById(id).orElse(null);
    }
}
