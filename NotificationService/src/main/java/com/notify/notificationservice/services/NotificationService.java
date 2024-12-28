package com.notify.notificationservice.service;

import com.notify.notificationservice.model.Email;
import com.notify.notificationservice.model.Sms;

public interface NotificationService {

	public ResponseEntity<?> sendEmail(Email mail);
	
	public ResponseEntity<?> sendSMS(Sms sms);
	
	public ResponseEntity<?> batchSendEmails(Email mail);
	
	public ResponseEntity<?> batchSendSMS(Sms sms);
	
}