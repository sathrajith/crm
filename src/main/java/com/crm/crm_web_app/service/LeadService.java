package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.Lead;
import com.crm.crm_web_app.entity.LeadScoreHistory;
import com.crm.crm_web_app.repository.LeadRepository;
import com.crm.crm_web_app.repository.LeadScoreHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LeadService {

    private static final Logger logger = LoggerFactory.getLogger(LeadService.class);

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private LeadScoreHistoryRepository leadScoreHistoryRepository;

    @Autowired
    private EmailService emailService;

    // Score the lead based on criteria and segment it
    public void scoreLead(Lead lead) {
        int score = 0;
        logger.info("Scoring lead with ID: {}", lead.getId());

        // Example scoring rules
        if (lead.getSource().equals("Website")) {
            score += 10;
        }
        if (lead.getEmail() != null && !lead.getEmail().isEmpty()) {
            score += 5;
        }
        if (lead.getPhoneNumber() != null && !lead.getPhoneNumber().isEmpty()) {
            score += 5;
        }

        lead.setScore(score);

        // Segment the lead based on the score
        String segment = segmentLead(lead);
        lead.setSegment(segment);

        // Log lead score history
        logLeadScoreHistory(lead);

        leadRepository.save(lead); // Save the lead with the updated score and segment

        // Notify sales team if lead is Hot
        if ("Hot".equals(segment)) {
            notifySalesTeam(lead);
        }
    }

    // Method to determine the segment based on the lead's score
    public String segmentLead(Lead lead) {
        if (lead.getScore() >= 20) {
            return "Hot";
        } else if (lead.getScore() >= 10) {
            return "Warm";
        } else {
            return "Cold";
        }
    }

    // Validate lead fields (e.g., email, phone number)
    public boolean validateLead(Lead lead) {
        return lead.getEmail() != null && lead.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$") &&
                lead.getPhoneNumber() != null && lead.getPhoneNumber().matches("^[0-9]{10}$");
    }

    // Log lead score history
    public void logLeadScoreHistory(Lead lead) {
        LeadScoreHistory history = new LeadScoreHistory();
        history.setLeadId(lead.getId());
        history.setScore(lead.getScore());
        history.setDateAssigned(LocalDateTime.now());
        leadScoreHistoryRepository.save(history);
    }

    // Assign lead to a sales representative based on score
    public void assignLeadToRep(Lead lead) {
        String salesRep = lead.getScore() >= 20 ? "Senior Rep" : "Junior Rep";
        lead.setAssignedTo(salesRep);
        leadRepository.save(lead);
    }

    // Send a notification to the sales team if a lead is Hot
    public void notifySalesTeam(Lead lead) {
        if ("Hot".equals(lead.getSegment())) {
            emailService.sendHotLeadNotification(lead);
            logger.info("Hot lead notification sent for lead ID: {}", lead.getId());
        }
    }

    // Get all leads
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    // Get leads by segment
    public List<Lead> getLeadsBySegment(String segment) {
        return leadRepository.findBySegment(segment); // Make sure your repository supports this query
    }

    // Get a lead by ID
    public Lead getLeadById(Long leadId) {
        Optional<Lead> lead = leadRepository.findById(leadId);
        if (lead.isPresent()) {
            return lead.get();
        } else {
            throw new IllegalArgumentException("Lead not found with ID: " + leadId);
        }
    }

    // Archive a lead (e.g., if no longer relevant)
    public void archiveLead(Lead lead) {
        lead.setArchived(true);
        leadRepository.save(lead);
        logger.info("Lead ID: {} archived", lead.getId());
    }

    // Delete a lead
    public void deleteLead(Long leadId) {
        leadRepository.deleteById(leadId);
        logger.info("Lead ID: {} deleted", leadId);
    }

    // Track lead source performance
    public void trackLeadSource(String source) {
        // Assuming you have a statistics service or repository to track lead sources
        leadRepository.updateLeadSourceStatistics(source);
        logger.info("Lead source tracked: {}", source);
    }
}
