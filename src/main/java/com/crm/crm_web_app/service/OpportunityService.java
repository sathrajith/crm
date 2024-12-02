package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.Opportunity;
import com.crm.crm_web_app.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpportunityService {
    @Autowired
    private OpportunityRepository opportunityRepository;

    public Opportunity saveOpportunity(Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    public List<Opportunity> getAllOpportunities() {
        return opportunityRepository.findAll();
    }
}

