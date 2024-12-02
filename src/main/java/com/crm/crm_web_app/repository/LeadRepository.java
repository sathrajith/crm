package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.Lead;
import com.crm.crm_web_app.entity.LeadStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    // Custom query to count the number of leads from a given source
    @Query("SELECT COUNT(l) FROM Lead l WHERE l.source = ?1")
    Long countLeadsBySource(String source);

    // Find or create/update LeadStatistics for a given source
    @Transactional
    @Modifying
    @Query("UPDATE LeadStatistics ls SET ls.leadCount = ls.leadCount + 1 WHERE ls.source = :source")
    void updateLeadSourceStatistics(String source);
    List<Lead> findLeadsBySource(String source);
    List<Lead> findBySegment(String segment);


}
