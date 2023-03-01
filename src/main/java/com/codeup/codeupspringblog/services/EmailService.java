package com.codeup.codeupspringblog.services;

import com.codeup.codeupspringblog.models.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Value("${spring.mail.from}")
    private String from;

    @Value("${CUSTOM_KEY}")
    private String customKey;

    public void prepareAndSend(Ad ad) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(ad.getUser().getEmail());
        msg.setSubject("Ad created with email send");
        msg.setText(String.format("Ad title: '%s'%nAd description: '%s'", ad.getTitle(), ad.getDescription()));

        try{
            this.emailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}