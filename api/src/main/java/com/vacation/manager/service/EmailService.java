package com.vacation.manager.service;

import com.vacation.manager.exception.AppExceptionBuilder;
import com.vacation.manager.messages.EmailsMessages;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final String url;
    private final MimeMessage mimeMessage;
    private final MimeMessageHelper mimeMessageHelper;

    public EmailService(JavaMailSender javaMailSender, @Value("${app.url}") String url) throws MessagingException {
        this.javaMailSender = javaMailSender;
        this.url = url;

        this.mimeMessage = javaMailSender.createMimeMessage();
        this.mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

    }

    public void sendEmailMessageToNewCeo(String email, String enterpriseName)  {
        try {
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("vacation-manager-app");
            mimeMessageHelper.setText(EmailsMessages.ACTIVATE_MESSAGE(email, enterpriseName, url), true);
            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException e){
            throw new AppExceptionBuilder().addError(EmailsMessages.CREATE_FAILURE).build();
        }
    }

    public void sendEmailToNewEmployee(String email, String enterpriseName, String passwd) {
        try {
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("vacation-manager-app");
            mimeMessageHelper.setText(EmailsMessages.ACTIVATE_EMPLOYEE_MESSAGE(email, enterpriseName, url, passwd), true);
            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException e){
            throw new AppExceptionBuilder().addError(EmailsMessages.CREATE_FAILURE).build();
        }
    }
}
