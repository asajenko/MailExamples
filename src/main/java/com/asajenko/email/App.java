package com.asajenko.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
//        To send email using JavaMail uncomment this lines:
//        new JavaMailEmailService().sendEmail();
//        new JavaMailEmailService().sendEmailWithAttachment();
    }
}
