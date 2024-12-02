package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
}

