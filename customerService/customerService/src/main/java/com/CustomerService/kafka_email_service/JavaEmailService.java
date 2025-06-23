package com.CustomerService.kafka_email_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class JavaEmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void javaMailServices(EmailDto dto){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(dto.getEmailFrom());
        mailMessage.setTo(dto.getEmailTo());
        mailMessage.setSubject(dto.getEmailSubject());
        mailMessage.setText(dto.getEmailMessage());
        try {
            emailSender.send(mailMessage);
            System.out.println("✅ Email sent successfully.");
        } catch (MailException e) {
            System.err.println("❌ Failed to send email:");
            e.printStackTrace();
        }
    }
}
