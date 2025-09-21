package com.excellenceAcademy.email.controller;

import com.excellenceAcademy.email.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class EmailController {

    @Autowired
    private SendEmailService sendEmailService;

    // Data transfer object for user input
    public static class UserContactRequest {
        public String firstName;
        public String lastName;
        public String course;
        public String email;
        public String phoneNumber;
        public String message;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/send")
    public ResponseEntity<String> sendUserDetails(@RequestBody UserContactRequest userRequest) {
        // Prepare email content
        String subject = "New Contact Form Submission: " + userRequest.firstName + " " + userRequest.lastName;
        String body = "You have received a new inquiry from the website. Details are as follows:\n\n"
                + "Name: " + userRequest.firstName + " " + userRequest.lastName + "\n"
                + "Course: " + userRequest.course + "\n"
                + "Email: " + userRequest.email + "\n"
                + "Phone Number: " + userRequest.phoneNumber + "\n"
                + "Message: " + userRequest.message + "\n\n"
                + "Regards,\nExcellence Academy";

        // Send email to a fixed recipient
        boolean isSent = sendEmailService.sendEmail("rohit@rworldsoftware.in", body, subject);
        if (isSent) {
            return new ResponseEntity<>("Your message has been sent successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to send your message. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
