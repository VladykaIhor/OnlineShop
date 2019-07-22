package mate.academy.service.impl;

import mate.academy.model.Code;
import mate.academy.service.MailService;
import org.apache.log4j.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailServiceImpl implements MailService {

    private static final Logger logger = Logger.getLogger(MailServiceImpl.class);


    @Override
    public void sendConfirmCode(Code code, String email) {
        final String login = "yngwar95@gmail.com";
        final String password = "y8132781327";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(login, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(login));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Confirm password \"Online shop\"");
            message.setText(code.getCode());

            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("An error happened while sending message to " + email, e);
        }
    }
}
