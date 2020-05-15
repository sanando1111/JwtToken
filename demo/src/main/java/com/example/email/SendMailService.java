package com.example.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Email;

@Service
public class SendMailService {
	private static final Logger log = LoggerFactory.getLogger(SendMailService.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Transactional
	public void sendMessage(Email email) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email.getTo());

		msg.setSubject(email.getSubject());
		msg.setText(email.getMessage());

		javaMailSender.send(msg);
		log.info("Message delivered successfully:", msg);

	}

}
