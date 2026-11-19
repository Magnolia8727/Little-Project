package com.example.MyLittleProject.services;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    //comes from spring-boot-starter-mail, is like a postmail, used to send emails
    private final JavaMailSender mailSender;
    @Value("${app.mail.from}")
    private String fromEmail;


    public void sendEmail(String to, String subject, String body)
    {
        SimpleMailMessage message = new SimpleMailMessage(); // This creates an email to send, consisting of all email parts
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
