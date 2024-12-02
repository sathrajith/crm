package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.LeadStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadStatisticsRepository extends JpaRepository<LeadStatistics, Long> {

    // Find statistics for a given source
    LeadStatistics findBySource(String source);
}
