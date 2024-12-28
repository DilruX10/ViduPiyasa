package com.pay.paymentservice.service;

import com.pay.paymentservice.model.Payment;

public interface ICourseService {

	public ResponseEntity<?> getPaymentById(String paymentId);
	
	public ResponseEntity<?> addPayment(Payment payment);
	
}