package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.entity.Lead;
import com.crm.crm_web_app.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadService leadService;

    @Autowired
    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    // Create or update a lead score
    @PostMapping("/score")
    public ResponseEntity<String> scoreLead(@RequestBody Lead lead) {
        leadService.scoreLead(lead);
        return ResponseEntity.status(HttpStatus.CREATED).body("Lead scored successfully.");
    }

    // Get all leads
    @GetMapping
    public ResponseEntity<List<Lead>> getAllLeads() {
        List<Lead> leads = leadService.getAllLeads();
        return ResponseEntity.ok(leads);
    }

    // Get leads by segment
    @GetMapping("/segment/{segment}")
    public ResponseEntity<List<Lead>> getLeadsBySegment(@PathVariable String segment) {
        List<Lead> leads = leadService.getLeadsBySegment(segment);
        if (leads.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(leads);
        }
        return ResponseEntity.ok(leads);
    }

    // Archive a lead
    @PostMapping("/archive/{leadId}")
    public ResponseEntity<String> archiveLead(@PathVariable Long leadId) {
        Lead lead = leadService.getLeadById(leadId);
        if (lead == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lead not found.");
        }
        leadService.archiveLead(lead);
        return ResponseEntity.ok("Lead archived successfully.");
    }

    // Delete a lead
    @DeleteMapping("/{leadId}")
    public ResponseEntity<String> deleteLead(@PathVariable Long leadId) {
        Lead lead = leadService.getLeadById(leadId);
        if (lead == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lead not found.");
        }
        leadService.deleteLead(leadId);
        return ResponseEntity.ok("Lead deleted successfully.");
    }
    @GetMapping("/{leadId}")
    public Lead getLeadById(@PathVariable Long leadId) {
        return leadService.getLeadById(leadId);
    }

}
