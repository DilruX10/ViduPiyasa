package com.pay.courseservice.repository;

import com.pay.paymentservice.model.Payment;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {

}
