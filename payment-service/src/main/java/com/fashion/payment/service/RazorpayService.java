package com.fashion.payment.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RazorpayService {

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    public Map<String, String> createOrder(int amount) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100); // Amount in paise (₹1 = 100 paise)
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_123456");
        orderRequest.put("payment_capture", 1); // Auto capture

        Order order = razorpay.orders.create(orderRequest);

        // Prepare response
        Map<String, String> response = new HashMap<>();
        response.put("orderId", order.get("id"));
        response.put("currency", order.get("currency"));
        response.put("amount", order.get("amount").toString());

        return response;
    }
}
