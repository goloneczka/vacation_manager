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

    private void setEmailParams(String email) throws EmailException {
        htmlEmail.setFrom("vacationmanagerapp@gmail.com" );
        htmlEmail.addTo(email);
        htmlEmail.setSubject("vacation-manager-app");
    }

    public void sendEmailMessageToNewCeo(String email, int enterpriseId)  {
        try {
            setEmailParams(email);
            htmlEmail.setHtmlMsg(EmailsMessages.ACTIVATE_MESSAGE(email, enterpriseId, url));
            htmlEmail.send();
        }
        catch (EmailException e){
            throw new AppExceptionBuilder().addError(EmailsMessages.CREATE_FAILURE).build();
        }

    }

    public void sendEmailToNewEmployee(String email, int enterpriseId, String passwd) {
        try {
            setEmailParams(email);
            htmlEmail.setHtmlMsg(EmailsMessages.ACTIVATE_EMPLOYEE_MESSAGE(email, enterpriseId, url, passwd));
            htmlEmail.send();
        }
        catch (EmailException e){
            throw new AppExceptionBuilder().addError(EmailsMessages.CREATE_FAILURE).build();
        }
    }
}
