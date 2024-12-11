package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, Long> {
    List<Segment> findByNameContaining(String name);  // Optional: For searching segments by name
}
