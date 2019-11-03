package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.email.SendMailService;
import com.example.jwt.JwtRequest;
import com.example.jwt.JwtResponse;
import com.example.jwt.JwtTokenUtil;
import com.example.jwt.JwtUserDetailsService;
import com.example.model.Email;
import com.example.model.OrderTransaction;

import org.springframework.security.core.userdetails.UserDetails;

@RestController
@CrossOrigin
public class HelloWorldController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private SendMailService sendMailService;

	private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

	@RequestMapping("/hello")
	public String firstPage() {
		return "Hello World";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		log.info("User name:" + authenticationRequest.getUsername());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));

	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public void send(@RequestBody OrderTransaction transaction) {
		log.info("Sending a transaction.");
		// Post message to the message queue named "OrderTransactionQueue"
		jmsTemplate.convertAndSend("OrderTransactionQueue", transaction);
	}

	@RequestMapping(value = "/mail", method = RequestMethod.POST)
	public void sendMail(@RequestBody Email email) {
		log.info("Sending an email");
		sendMailService.sendMessage(email);
	}
}
