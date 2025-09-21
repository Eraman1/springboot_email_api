package com.excellenceAcademy.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private final String fromEmailId = "abhishek261286@gmail.com"; // Replace with your sender email

    public boolean sendEmail(String recipient, String body, String subject) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(fromEmailId);
            simpleMailMessage.setTo(recipient);
            simpleMailMessage.setText(body);
            simpleMailMessage.setSubject(subject);

            javaMailSender.send(simpleMailMessage);
            return true;
        } catch (MailException e) {
            // Log the exception (use a logger in production)
            System.err.println("Error sending email: " + e.getMessage());
            return false;
        }
    }
}
