package com.crm.crm_web_app.components;

import com.crm.crm_web_app.entity.Lead;
import com.crm.crm_web_app.entity.Customer;
import com.crm.crm_web_app.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WorkflowListener {

    private static final Logger logger = LoggerFactory.getLogger(WorkflowListener.class);
    private final EmailService emailService;

    public WorkflowListener(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Handles workflow actions when a lead's status changes.
     * This method is manually invoked from the service layer.
     *
     * @param lead the updated lead entity
     */
    public void onLeadStatusChanged(Lead lead) {
        if (lead == null || lead.getStatus() == null) {
            logger.warn("Lead or lead status is null. Skipping workflow actions.");
            return;
        }

        Customer customer = lead.getCustomer();
        if (customer == null || customer.getEmail() == null || customer.getName() == null) {
            logger.warn("Customer information is incomplete. Skipping email notification.");
            return;
        }

        sendStatusUpdateNotification(customer.getEmail(), customer.getName(), lead.getStatus());
    }

    /**
     * Sends a notification email regarding the lead status update.
     *
     * @param email  the recipient's email address
     * @param name   the recipient's name
     * @param status the updated status of the lead
     */
    private void sendStatusUpdateNotification(String email, String name, String status) {
        String subject = "Lead Status Updated";
        String body = "Dear " + name + ",\n\n"
                + "The status of your lead has been updated to: " + status + ".\n\n"
                + "Best Regards,\nCRM Team";

        try {
            emailService.sendEmail(email, subject, body);
            logger.info("Status update email sent to {} for lead status: {}", email, status);
        } catch (Exception e) {
            logger.error("Failed to send status update email to {}: {}", email, e.getMessage());
        }
    }


    public void handleLeadStatusChange(Lead lead) {
        if (lead.getStatus() != null) {
            System.out.println("Handling status change for lead: " + lead.getId());
        }
    }
}
