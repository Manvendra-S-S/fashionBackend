package com.fashion.payment.controller;

import com.fashion.payment.dto.Orderdto;
import com.fashion.payment.model.MailStructure;
import com.fashion.payment.service.EmailService;
import com.fashion.payment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orderDetails")
    public ResponseEntity<?> addAddress(@RequestBody Orderdto order){
        orderService.processOrder(order);
        return null;
    }
}
