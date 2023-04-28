package com.example.StudentApplication.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String email, String username) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String body="Welcome "+username+" !";
        helper.setTo(email);
        helper.setSubject("Welcome Student  Registered Successfully and Your Java Tutorial Is completed...");
        helper.setText(body, true);
        javaMailSender.send(message);
    }
}
