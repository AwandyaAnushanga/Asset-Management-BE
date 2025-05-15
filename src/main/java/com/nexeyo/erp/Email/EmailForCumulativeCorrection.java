package com.nexeyo.erp.Email;

import com.nexeyo.erp.Config.Config;
import com.nexeyo.erp.Config.ConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Properties;

@Service
public class EmailForCumulativeCorrection {
    @Autowired
    ConfigRepo configRepo;


    public boolean MailSend(String subject, String content, ByteArrayOutputStream baos, String username, String password, String to_email, List<Config> cc_email) {


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
            for (int i = 0; i < cc_email.size(); i++) {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc_email.get(i).getValue()));
            }
            message.setSubject(subject);

            // Create a multipart message
            MimeMultipart multipart = new MimeMultipart();

            // Part 1: HTML content
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(content, "text/html");
            multipart.addBodyPart(textPart);

            // Part 2: attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
//            File file = new File(); // Replace with the actual path to your PDF file
//            DataSource source = new FileDataSource(file);
            DataSource source = new ByteArrayDataSource(baos.toByteArray(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("Report.xlsx");
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);
            Transport.send(message);
            System.out.println("HTML Email sent successfully.");
            return true;
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
            return false;
        }
    }
}
