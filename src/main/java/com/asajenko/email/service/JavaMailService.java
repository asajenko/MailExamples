package com.asajenko.email.service;

import org.springframework.core.io.ClassPathResource;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

public class JavaMailService {

    private final String username = "you@yourdomain.com";
    private final String password = "pass";

    public void sendEmail() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("you@yourdomain.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("recipient@otherdomain.com"));
            message.setSubject("Send mail using JavaMail");
//            uncomment this line if you want to try html text simple msg
//            message.setContent("Hi!\n\n" +
//                    "To send this <b>mail</b> we are used JavaMail.","text/html");
            message.setText("Hi!\n\n" +
                    "To send this mail we are used JavaMail.");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailWithAttachment() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("you@yourdomain.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("recipient@otherdomain.com"));
            message.setSubject("Send mail with attachment using JavaMail");

            MimeBodyPart text = new MimeBodyPart();
            text.setContent("Hi!\n\n" +
                    "To send this <b>mail</b> we are used JavaMail.", "text/html");

            MimeBodyPart attachment = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(new ClassPathResource("test.txt").getFile());
            attachment.setDataHandler(new DataHandler(fds));
            attachment.setFileName("test.txt");

            Multipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(text);
            multiPart.addBodyPart(attachment);

            message.setContent(multiPart);

            Transport.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}
