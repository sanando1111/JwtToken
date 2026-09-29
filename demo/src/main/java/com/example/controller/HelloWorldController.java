package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.JwtRequest;
import com.example.jwt.JwtResponse;
import com.example.jwt.JwtTokenUtil;
import com.example.jwt.JwtUserDetailsService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@CrossOrigin
public class HelloWorldController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

	@RequestMapping("/hello")
	public String firstPage() {
		return "Hello World";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
		log.info("User name:"+authenticationRequest.getUsername());
		final UserDetails userDetails;
		try {
			userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		} catch (UsernameNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		if (!passwordEncoder.matches(authenticationRequest.getPassword(), userDetails.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));

	}
}
