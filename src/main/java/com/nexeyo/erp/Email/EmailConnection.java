package com.nexeyo.erp.Email;

import com.nexeyo.erp.Config.ConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;
@Service
public class EmailConnection {

    ConfigRepo configRepo;
    @Autowired
    public EmailConnection(ConfigRepo configRepo) {
        this.configRepo = configRepo;
    }
    public void MailSend(String subject, String content, String to_email, String path){

        final String username = configRepo.findByKey("email_username").getValue();
        final String password = configRepo.findByKey("email_password").getValue();


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.ciphers", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_email));
            message.setSubject(subject);

            // Create a multipart message
            MimeMultipart multipart = new MimeMultipart();

            // Part 1: HTML content
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(content, "text/html");
            multipart.addBodyPart(textPart);

            // Part 2: PDF attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            File pdfFile = new File(path); // Replace with the actual path to your PDF file
            DataSource source = new FileDataSource(pdfFile);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(pdfFile.getName());
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);
            Transport.send(message);
            System.out.println("HTML Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void MailSendWithoutFile(String subject, String content, String to_email){

        final String username = configRepo.findByKey("email_username").getValue();
        final String password = configRepo.findByKey("email_password").getValue();


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.ciphers", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_email));
            message.setSubject(subject);

//            // Set the content type to HTML
//            message.setContent(content, "text/html");
            // Create a multipart message
            MimeMultipart multipart = new MimeMultipart();

            // Part 1: HTML content
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(content, "text/html");
            multipart.addBodyPart(textPart);

            message.setContent(multipart);
            Transport.send(message);
            System.out.println("HTML Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
