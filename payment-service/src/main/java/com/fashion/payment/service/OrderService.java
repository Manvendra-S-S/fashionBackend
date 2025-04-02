package com.fashion.payment.service;

import com.fashion.payment.dto.Orderdto;
import com.fashion.payment.model.MailStructure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private  EmailService emailService;


    public ResponseEntity<?> processOrder(Orderdto order){
        MailStructure mailStructure = new MailStructure();
        mailStructure.setSubject("Testing email service !");
        mailStructure.setMessage(" Testing successfully completed ! ");
        emailService.sendEmail(mailStructure,order.getEmail());
        return null;
    }

}
