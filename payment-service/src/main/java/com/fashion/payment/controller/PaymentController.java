package com.fashion.payment.controller;

import com.fashion.payment.service.RazorpayService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
public class PaymentController {

    @Autowired
    private RazorpayService razorpayService;

    @PostMapping("/create-order")
    public Map<String, String> createOrder(@RequestParam int amount) throws RazorpayException {
        return razorpayService.createOrder(amount);
    }
}
