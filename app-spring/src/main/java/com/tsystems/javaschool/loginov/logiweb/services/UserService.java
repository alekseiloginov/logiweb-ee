package com.tsystems.javaschool.loginov.logiweb.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Service class that uses Hibernate DAO classes to work with User objects.
 */
@Service
public class UserService {
    private static final Logger LOG = Logger.getLogger(UserService.class);

    public void remindPassword(String email) throws UnsupportedEncodingException {
        final String username = email;
        final String pass = "D25112005d";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, pass);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("logiweb@gmail.com", "Logiweb"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Password Reminder");
            message.setText("Dear Alex,"
                    + "\n\n Here is your forgotten password: ####");

            Transport.send(message);

        } catch (MessagingException e) {
            LOG.error("MessagingException", e);
        }
    }
}
