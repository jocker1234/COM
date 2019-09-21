package com.com.backend.service.serviceImpl;

import com.com.backend.model.Users;
import com.com.backend.dto.Mail;
import com.com.backend.dto.UsersDto;
import com.com.backend.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImp implements EmailService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String CREATE_EMAIL_TEMPLATE = "mail/creationEmail";
    private static final String PASSWORD_RESET_EMAIL_TEMPLATE = "mail/passwordResetEmail";

    private JavaMailSender emailSender;
    private SpringTemplateEngine templateEngine;

    public EmailServiceImp(JavaMailSender emailSender, SpringTemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendCreateEmail(UsersDto user) {
        log.debug("Sending creation account email to '{}'", user.getEmail());
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        context.setVariable("date", "17-18 of May 2019");
        String body = templateEngine.process(CREATE_EMAIL_TEMPLATE, context);
        Mail mail = new Mail(user.getEmail(), CREATE_EMAIL_TEMPLATE, body);
        sendEmail(mail);
    }

    @Async
    public void reamindPassword(Users user) {
        log.debug("Sending password reset email to '{}'", user.getEmail());
        Context context = new Context();
        context.setVariable("email",user.getEmail());
        context.setVariable("password", user.getPassword());
        String body = templateEngine.process(PASSWORD_RESET_EMAIL_TEMPLATE, context);
        Mail mail = new Mail(user.getEmail(), PASSWORD_RESET_EMAIL_TEMPLATE, body);
        sendEmail(mail);

    }

    private void sendEmail(Mail mail) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        emailSender.send(message);
    }
}
