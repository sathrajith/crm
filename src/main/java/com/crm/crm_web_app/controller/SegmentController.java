package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.entity.Segment;
import com.crm.crm_web_app.service.SegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/segments")
public class SegmentController {

    private final SegmentService segmentService;

    @Autowired
    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    // Create or update a segment
    @PostMapping
    public ResponseEntity<Segment> createOrUpdateSegment(@RequestBody Segment segment) {
        Segment savedSegment = segmentService.saveSegment(segment);
        return ResponseEntity.ok(savedSegment);
    }

    // Get a segment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Segment> getSegmentById(@PathVariable Long id) {
        Optional<Segment> segment = segmentService.getSegmentById(id);
        return segment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all segments
    @GetMapping
    public ResponseEntity<List<Segment>> getAllSegments() {
        List<Segment> segments = segmentService.getAllSegments();
        return ResponseEntity.ok(segments);
    }

    // Delete a segment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSegmentById(@PathVariable Long id) {
        segmentService.deleteSegmentById(id);
        return ResponseEntity.noContent().build();
    }

    // Search for segments by name
    @GetMapping("/search")
    public ResponseEntity<List<Segment>> searchSegments(@RequestParam String name) {
        List<Segment> segments = segmentService.searchSegmentsByName(name);
        return ResponseEntity.ok(segments);
    }

    // Additional endpoint for analyzing segments by revenue could be added here
}
