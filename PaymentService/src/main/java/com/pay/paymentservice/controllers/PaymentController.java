package com.pay.paymentservice.controller;

import com.pay.paymentservice.model.Payment;
import com.pay.paymentservice.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getPaymentById(@PathVariable String paymentId){
        return paymentService.getPaymentById(paymentId);
    }

    @PostMapping("/")
    public ResponseEntity<?> addPayment(@RequestBody Payment payment){
        return paymentService.insertPayment(payment);
    }

}
