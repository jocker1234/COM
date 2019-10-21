package com.com.backend.service.serviceImpl;

import com.com.backend.dao.UsersDao;
import com.com.backend.exception.AppException;
import com.com.backend.model.Abstracts;
import com.com.backend.model.Users;
import com.com.backend.dto.Mail;
import com.com.backend.dto.UsersDto;
import com.com.backend.model.enums.Status;
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
    private static final String APPROVED_ABSTRACT = "mail/approvedAbstract";
    private static final String REJECTED_ABSTRACT = "mail/rejectedAbstract";

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
        log.info("Success send");
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

    @Async
    @Override
    public void sendDecisionAboutAbstract(Users user, Abstracts abstracts) throws AppException {
        log.debug("Sending decision about abstract to '{}'", user.getEmail());
        Context context = new Context();
        String body;
        if(abstracts.getStatus().equals(Status.APPROVED.getStatus())) {
            body = templateEngine.process(APPROVED_ABSTRACT, context);
        } else if(abstracts.getStatus().equals(Status.REJECTED.getStatus())){
            body = templateEngine.process(REJECTED_ABSTRACT, context);
        } else {
            throw new AppException();
        }
        Mail mail = new Mail(user.getEmail(), APPROVED_ABSTRACT, body);
        sendEmail(mail);
    }

    @Async
    public void sendSingleMail(Mail mail) {
        sendEmail(mail);
    }

    private void sendEmail(Mail mail) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setFrom("p.gogol@student.pb.edu.pl");
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        emailSender.send(message);
    }
}
