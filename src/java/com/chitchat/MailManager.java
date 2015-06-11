package com.chitchat;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailManager {

    private static Session session;
    final static String from = "unisddn@gmail.com";
    final static String passwd = "@abc#123";       //the mail id to send the email
    final static String usern = "heed";         //proxy authentication
    final static String pas = "ravi";

    static {
        Properties props = System.getProperties();
        //props.setProperty("proxySet", "true");
       // props.setProperty("http.proxyHost", "172.31.100.14");
       // props.setProperty("http.proxyPort", "3128");
        //  Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
       // props.put("mail.smtp.socks.host","172.31.100.14");
        //props.put("mail.smtp.socks.port","3128");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //Authenticator auth = new SMTPAuthenticator();
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, passwd);
            }
        });
       // System.setProperty("java.net.socks.username", usern);             
        //System.setProperty("java.net.socks.password", pas);
    }

    public static boolean sendMail(String to) {
        boolean flag = true;
        String subject = "Registration Confirmed";
        String content = "Your registration is successful.";

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
        } catch (Exception e) {
            flag = false;
            System.out.println(e);
        }
        return flag;
    }
}
/*
class SMTPAuthenticator extends javax.mail.Authenticator
{
    public PasswordAuthentication getPasswordAuthentication()
    {
        String username = "edcguest";
        String password = "edcguest";
        return new PasswordAuthentication(username, password);
    }
}
*/
