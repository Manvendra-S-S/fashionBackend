package com.fashion.payment.service;

import com.fashion.payment.model.MailStructure;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    private final String CLASS_NAME = "EmailService";
    @Autowired
    private JavaMailSender mailSender;
    Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Value("${spring.mail.username}")
    private String senderMailUsername;
    /**
     * Send mail to a recipient with plain text as email body
     *
     * @param mailStructure   email request payload containing subject and text
     * @param recipientMailId single recipient email ID
     * @return mailResponse "SUCCESS" or "FAILURE"
     */
    public String sendEmail(MailStructure mailStructure, String recipientMailId) {
        final String methodName = "sendEmail";
        logger.info("== ClassName :: {}, MethodName :: {} STARTS ==", CLASS_NAME, methodName);
        String mailResponse;
        try {
            final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(senderMailUsername);
            simpleMailMessage.setSubject(mailStructure.getSubject().trim());
            simpleMailMessage.setText(mailStructure.getMessage().trim());
            simpleMailMessage.setTo(recipientMailId);
            mailSender.send(simpleMailMessage);
            mailResponse = "SUCCESS";
        } catch (Exception ex) {
            logger.error("Exception occurred while sending mail to :: {}", recipientMailId);
            logger.error("Exception reason :: {}", ex.getMessage());
            ex.printStackTrace();
            mailResponse = "FAILURE";
        }
        logger.info("*** ClassName :: {}, MethodName :: {} ENDS ***", CLASS_NAME, methodName);
        return mailResponse;
    }
}
