package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.Segment;
import com.crm.crm_web_app.repository.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SegmentService {

    private final SegmentRepository segmentRepository;

    @Autowired
    public SegmentService(SegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }

    // Create or update a segment
    public Segment saveSegment(Segment segment) {
        return segmentRepository.save(segment);
    }

    // Get a segment by ID
    public Optional<Segment> getSegmentById(Long id) {
        return segmentRepository.findById(id);
    }

    // Get all segments
    public List<Segment> getAllSegments() {
        return segmentRepository.findAll();
    }

    // Delete a segment by ID
    public void deleteSegmentById(Long id) {
        segmentRepository.deleteById(id);
    }

    // Search for segments by name
    public List<Segment> searchSegmentsByName(String name) {
        return segmentRepository.findByNameContaining(name);
    }

    // Additional logic for analyzing segments based on revenue could be added here
}
