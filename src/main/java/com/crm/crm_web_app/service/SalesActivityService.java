package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.SalesActivity;
import com.crm.crm_web_app.repository.SalesActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesActivityService {

    @Autowired
    private SalesActivityRepository salesActivityRepository;

    public SalesActivity createSalesActivity(SalesActivity salesActivity) {
        return salesActivityRepository.save(salesActivity);
    }

    public List<SalesActivity> getAllSalesActivities() {
        return salesActivityRepository.findAll();
    }

    public SalesActivity getSalesActivityById(Long id) {
        return salesActivityRepository.findById(id).orElse(null);
    }
}
