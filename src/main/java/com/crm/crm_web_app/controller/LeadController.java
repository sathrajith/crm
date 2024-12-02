package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.entity.Lead;
import com.crm.crm_web_app.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    // Create or update a lead
    @PostMapping("/score")
    public void scoreLead(@RequestBody Lead lead) {
        leadService.scoreLead(lead);
    }

    // Get all leads
    @GetMapping
    public List<Lead> getAllLeads() {
        return leadService.getAllLeads();
    }

    // Get leads by segment
    @GetMapping("/segment/{segment}")
    public List<Lead> getLeadsBySegment(@PathVariable String segment) {
        return leadService.getLeadsBySegment(segment);
    }

    // Archive a lead
    @PostMapping("/archive/{leadId}")
    public void archiveLead(@PathVariable Long leadId) {
        Lead lead = leadService.getLeadById(leadId);  // You can implement a method to get the lead by ID
        leadService.archiveLead(lead);
    }

    // Delete a lead
    @DeleteMapping("/{leadId}")
    public void deleteLead(@PathVariable Long leadId) {
        leadService.deleteLead(leadId);
    }
}
