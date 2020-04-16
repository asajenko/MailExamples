package com.asajenko.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Component
public class SpringEmailService implements CommandLineRunner {

    private final JavaMailSender javaMailSender;

    @Autowired
    public SpringEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("recipient@otherdomain.com");
        message.setSubject("Spring example mail");
        message.setText("Hi!\n\n" +
                "To send this mail we have used spring framework.");

        javaMailSender.send(message);
    }

    private void sendEmailWithAttachment() {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("recipient@otherdomain.com");
            helper.setSubject("Spring example mail with attachment");
            helper.setText("Hi!\n\n" +
                    "To send this <b>mail</b> we have used spring framework.", true);

            helper.addAttachment("testFile.txt", new ClassPathResource("test.txt").getFile());

            javaMailSender.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run(String... args) {
        sendMail();
        sendEmailWithAttachment();
    }
}
