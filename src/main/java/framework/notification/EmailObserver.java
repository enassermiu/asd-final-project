package framework.notification;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailObserver implements Observer {

    @Override
    public void send(String destination, String outgoingMessage) {


        String to = destination;


        String from = "asdbankprojectnotifier@gmail.com";


        String host = "smtp.gmail.com";


        Properties properties = System.getProperties();


        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");


        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new javax.mail.PasswordAuthentication("asdbankprojectnotifier@gmail.com", "group7password");

            }

        });


        session.setDebug(true);

        try {

            MimeMessage message = new MimeMessage(session);


            message.setFrom(new InternetAddress(from));


            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destination));


            message.setSubject("This is the Subject Line!");


            message.setContent(
                    outgoingMessage,
                    "text/html");

            System.out.println("sending...");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (Exception mex) {
            mex.printStackTrace();
        }

    }
}
