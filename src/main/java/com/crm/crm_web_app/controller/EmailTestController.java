package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailTestController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-test-email")
    public String sendTestEmail(@RequestParam String email) {
        try {
            String subject = "Test Email";
            String body = "This is a test email.If you received this, I Love you rendu heart emoji.ithula emoji epdi use pandradhunu therla!";
            emailService.sendReport(email, subject, body);
            return "Test email sent successfully!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send test email.";
        }
    }
}
