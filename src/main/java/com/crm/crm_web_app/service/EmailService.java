package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.Lead;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendReport(String toEmail, String subject, String body) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendHotLeadNotification(Lead lead){
        System.out.println("Sending hot lead notifiaction for lead ID: "+ lead.getId());
    }
    public void sendWelcomeEmail(String to, String name){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Welcom to CRM");
        message.setText("Hello "+ ",/n/nWelcome to CRM system!/n/nRegards,/nCRM Team");
        mailSender.send(message);
    }
    public void sendLeadStatusNotification(String to, String leadName, String status){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Lead status notification for lead ID: "+ leadName);
        message.setText("Dear User, /n/nThe status of lead " + leadName+ " has been updated to " + status + "./n/nRegards,/nCRM Team");
        mailSender.send(message);
    }
    public void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
}
