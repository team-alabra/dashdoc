package com.dashdocnow.services.vendors;

import com.dashdocnow.DTO.SMSRequestDTO;
import com.dashdocnow.services.SMSTemplateService;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// email setup reference: https://www.baeldung.com/java-email
import com.twilio.type.PhoneNumber;
@Service
public class NotificationsService {
    private JavaMailSender emailSender;
    @Value("${google.auth.email}")
    private String googleUsername;
    @Value("${twilio.number}")
    private String twilioNumber;
    @Autowired
    private SMSTemplateService smsTemplateService;

    public NotificationsService(JavaMailSender _emailSender) {
        emailSender = _emailSender;
    }

    public String sendText(SMSRequestDTO smsRequest) {
        try {
            var messageContent = smsTemplateService.getMessageContent(smsRequest);
            var res = Message.creator(
                new PhoneNumber("+17184904016"),
                new PhoneNumber(twilioNumber),
                messageContent)
            .create();

            return messageContent;
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public MimeMessage sendNotificationEmail(String destination, String subject, String content) throws MessagingException {
        try {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(googleUsername);
            helper.setTo(destination);
            helper.setSubject(subject);
            // TODO: Setup templates for content
            helper.setText("<b>"+ content +"</b>");

            emailSender.send(message);

            return message;
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
