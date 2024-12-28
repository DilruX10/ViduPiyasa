package com.notify.notificationservice.controller;

import com.notify.notificationservice.model.Email;
import com.notify.notificationservice.model.Sms;

import com.notify.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@PostMapping("/email")
	public ResponseEntity<?> sendEmail(@RequestBody Email mail){
		return notificationService.sendEmail(mail);
	}
	
	@PostMapping("/sms")
	public ResponseEntity<?> sendSMS(@RequestBody Sms sms){
		return notificationService.sendSMS(sms);
	}

	@PostMapping("/batchmail")
	public ResponseEntity<?> batchSendEmails(@RequestBody Email mail){
		return notificationService.batchSendEmails(mail);
	}
	
	@PostMapping("/batchsms")
	public ResponseEntity<?> batchSendSMS(@RequestBody Sms sms){
		return notificationService.batchSendSMS(sms);
	}
}