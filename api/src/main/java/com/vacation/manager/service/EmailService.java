package com.vacation.manager.service;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final HtmlEmail htmlEmail;

    public EmailService(HtmlEmail htmlEmail) {
        this.htmlEmail = htmlEmail;
    }

    public void sendEmailMessage() throws EmailException {
        htmlEmail.setFrom("vacationmanagerapp@gmail.com" );
        htmlEmail.addTo("goleniewski6@gmail.com");
        htmlEmail.setSubject("test");
        htmlEmail.setMsg("<p style='font-size:16px;color:green'>Here is your example</p>");
        htmlEmail.send();
    }
}
