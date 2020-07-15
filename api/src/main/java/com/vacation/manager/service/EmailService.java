package com.vacation.manager.service;

import com.vacation.manager.exception.AppExceptionBuilder;
import com.vacation.manager.messages.EmailsMessages;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final HtmlEmail htmlEmail;
    private final String url;

    public EmailService(HtmlEmail htmlEmail, @Value("${app.url}") String url) {
        this.htmlEmail = htmlEmail;
        this.url = url;
    }

    public void sendEmailMessage(String email, int enterpriseId)  {
        try {
            htmlEmail.setFrom("vacationmanagerapp@gmail.com" );
            htmlEmail.addTo(email);
            htmlEmail.setSubject("vacation-manager-app");
            htmlEmail.setHtmlMsg(EmailsMessages.ACTIVATE_MESSAGE(email, enterpriseId, url));
            htmlEmail.send();
        }
        catch (EmailException e){
            throw new AppExceptionBuilder().addError(EmailsMessages.CREATE_FAILURE).build();
        }

    }
}
