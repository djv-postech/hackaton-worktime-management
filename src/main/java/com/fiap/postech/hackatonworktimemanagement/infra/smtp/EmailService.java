package com.fiap.postech.hackatonworktimemanagement.infra.smtp;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

public class EmailService {

    private final JavaMailSender javaEmailSender;

    public EmailService(JavaMailSender javaEmailSender) {
        this.javaEmailSender = javaEmailSender;
    }

    @Async
    public void enviarEmail(String para, String assunto, String corpo){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(para);
        simpleMailMessage.setFrom("mailtrap@demomailtrap.com");
        simpleMailMessage.setSubject(assunto);
        simpleMailMessage.setText(corpo);

        javaEmailSender.send(simpleMailMessage);
    }
}
