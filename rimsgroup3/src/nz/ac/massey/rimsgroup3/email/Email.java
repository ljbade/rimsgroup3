/*
 * Email class controls the function of sending an email to a Massey
 * staff member
 */

package nz.ac.massey.rimsgroup3.email;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Steve
 */
public class Email {

    Session session;
    Properties props;

    public Email() {
        props = new Properties();
        props.put("mail.smtp.host", "my-mail-server"); // smtp host needs to be set
        //props.put("mail.from", "me@example.com");  // sender can be be set here also
        session = Session.getInstance(props, null);
    }
    /* sendEmail method
     * to - required recipient of email
     * from - required sender of email
     * message - required body of email
     */
    public Boolean sendEmail(String to, String from, String message) {
        String subject = "A publication has been submitted to RMS";
        try {
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom();
        msg.setRecipients(Message.RecipientType.TO,
                          to);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);
        Transport.send(msg);
        return true;
    } catch (MessagingException mex) {
        return false;
    }


    }
}
