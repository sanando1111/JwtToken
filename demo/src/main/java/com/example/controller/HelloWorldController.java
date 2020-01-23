package com.example.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.email.SendMailService;
import com.example.exception.InternalServerErrorException;
import com.example.jwt.JwtRequest;
import com.example.jwt.JwtResponse;
import com.example.jwt.JwtTokenUtil;
import com.example.jwt.JwtUserDetailsService;
import com.example.model.Cards;
import com.example.model.Email;
import com.example.model.OrderTransaction;
import com.example.model.TaxProducts;
import com.example.service.CardsService;
import com.example.service.TaxProductsService;

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

	@Autowired
	private TaxProductsService taxProductService;

	@Autowired
	private CardsService cardsService;

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

	@RequestMapping(value = "/allcards", method = RequestMethod.GET)
	public List<Cards> getAllCards() {

		return cardsService.getAllCardDetails();
	}

	@RequestMapping(value = "/activecards", method = RequestMethod.GET)
	public List<Cards> getAllActiveCards() {

		return cardsService.getActiveCardDetails();
	}

	@ExceptionHandler(value = { InternalServerErrorException.class })
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public String addTaxProduct(@RequestBody TaxProducts product) {

		log.info("Add product request payload:" + product);
		return taxProductService.addProducts(product);
	}

	@RequestMapping(value = "/updateproduct", method = RequestMethod.POST)
	public String updateTaxProduct(@RequestBody TaxProducts product) {

		return taxProductService.updateProductAmount(product);
	}

	@RequestMapping(value = "/deleteproduct", method = RequestMethod.POST)
	public String deleteTaxProduct(@RequestBody TaxProducts product) {

		return taxProductService.deleteProduct(product);
	}

	@RequestMapping(value = "/alltaxproducts", method = RequestMethod.GET)
	public List<String> getAllTaxProducts() {
		return taxProductService.getActiveTaxProducts();
	}

	@RequestMapping(value = "/getproductdetails", method = RequestMethod.GET)
	public TaxProducts productDetails(@RequestParam String productName) {
		log.info("Sending details for product:"+productName);
		return taxProductService.getProductDetails(productName);
	}
}
