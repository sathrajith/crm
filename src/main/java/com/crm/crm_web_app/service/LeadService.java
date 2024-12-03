package com.crm.crm_web_app.service;

import com.crm.crm_web_app.components.WorkflowListener;
import com.crm.crm_web_app.entity.Lead;
import com.crm.crm_web_app.entity.LeadScoreHistory;
import com.crm.crm_web_app.repository.LeadRepository;
import com.crm.crm_web_app.repository.LeadScoreHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeadService {

    private static final Logger logger = LoggerFactory.getLogger(LeadService.class);

    private final LeadRepository leadRepository;
    private final LeadScoreHistoryRepository leadScoreHistoryRepository;
    private final EmailService emailService;
    private final WorkflowListener workflowListener;

    // Constructor-based dependency injection
    public LeadService(
            LeadRepository leadRepository,
            LeadScoreHistoryRepository leadScoreHistoryRepository,
            EmailService emailService,
            WorkflowListener workflowListener) {
        this.leadRepository = leadRepository;
        this.leadScoreHistoryRepository = leadScoreHistoryRepository;
        this.emailService = emailService;
        this.workflowListener = workflowListener;
    }

    // Score the lead based on defined criteria
    public void scoreLead(Lead lead) {
        logger.info("Scoring lead with ID: {}", lead.getId());

        int score = calculateLeadScore(lead);
        lead.setScore(score);

        // Assign a segment based on the score
        String segment = determineSegment(score);
        lead.setSegment(segment);

        // Log score history and save updated lead
        logLeadScoreHistory(lead);
        leadRepository.save(lead);

        // Notify the sales team if the lead is "Hot"
        if ("Hot".equals(segment)) {
            notifySalesTeam(lead);
        }
    }

    // Calculate lead score based on predefined rules
    private int calculateLeadScore(Lead lead) {
        int score = 0;

        if ("Website".equals(lead.getSource())) {
            score += 10;
        }
        if (lead.getEmail() != null && !lead.getEmail().isEmpty()) {
            score += 5;
        }
        if (lead.getPhoneNumber() != null && !lead.getPhoneNumber().isEmpty()) {
            score += 5;
        }

        return score;
    }

    // Determine the lead's segment based on its score
    private String determineSegment(int score) {
        if (score >= 20) {
            return "Hot";
        } else if (score >= 10) {
            return "Warm";
        } else {
            return "Cold";
        }
    }

    // Log the lead's score history
    private void logLeadScoreHistory(Lead lead) {
        LeadScoreHistory history = new LeadScoreHistory();
        history.setLeadId(lead.getId());
        history.setScore(lead.getScore());
        history.setDateAssigned(LocalDateTime.now());
        leadScoreHistoryRepository.save(history);
        logger.info("Logged score history for lead ID: {}", lead.getId());
    }

    // Notify the sales team about a "Hot" lead
    private void notifySalesTeam(Lead lead) {
        emailService.sendHotLeadNotification(lead);
        logger.info("Notification sent for hot lead with ID: {}", lead.getId());
    }

    // Update the lead's status and invoke workflow actions
    @Transactional
    public Lead updateLeadStatus(Long leadId, String newStatus) {
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new IllegalArgumentException("Lead not found with ID: " + leadId));

        lead.setStatus(newStatus);
        Lead updatedLead = leadRepository.save(lead);

        // Trigger workflow listener explicitly
        workflowListener.handleLeadStatusChange(updatedLead);

        logger.info("Updated status for lead ID: {} to {}", leadId, newStatus);
        return updatedLead;
    }

    // Assign the lead to a sales representative
    public void assignLeadToRep(Lead lead) {
        String salesRep = lead.getScore() >= 20 ? "Senior Rep" : "Junior Rep";
        lead.setAssignedTo(salesRep);
        leadRepository.save(lead);
        logger.info("Assigned lead ID: {} to {}", lead.getId(), salesRep);
    }

    // Retrieve all leads
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    // Retrieve leads by segment
    public List<Lead> getLeadsBySegment(String segment) {
        return leadRepository.findBySegment(segment);
    }

    // Retrieve a specific lead by ID
    public Lead getLeadById(Long leadId) {
        return leadRepository.findById(leadId)
                .orElseThrow(() -> new IllegalArgumentException("Lead not found with ID: " + leadId));
    }

    // Archive a lead
    @Transactional
    public void archiveLead(Lead lead) {
        lead.setArchived(true);
        lead.setArchivedAt(LocalDateTime.now());  // Assuming `archivedAt` field is added to the `Lead` entity
        leadRepository.save(lead);
        logger.info("Archived lead ID: {}", lead.getId());
    }

    // Delete a lead
    public void deleteLead(Long leadId) {
        try {
            leadRepository.deleteById(leadId);
            logger.info("Deleted lead ID: {}", leadId);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Lead with ID {} not found for deletion", leadId);
        }
    }

    // Track performance of a lead source
    public void trackLeadSource(String source) {
        leadRepository.updateLeadSourceStatistics(source); // Ensure repository supports this
        logger.info("Tracked lead source: {}", source);
    }
}
