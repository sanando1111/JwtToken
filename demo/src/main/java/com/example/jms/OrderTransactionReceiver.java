package com.example.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.model.OrderTransaction;

@Component
public class OrderTransactionReceiver {

	private static final Logger log = LoggerFactory.getLogger(OrderTransactionReceiver.class);

	@JmsListener(destination = "OrderTransactionQueue", containerFactory = "myFactory")
	public void receiveMessage(OrderTransaction transaction) {
		log.info("Received <" + transaction + ">");

	}
}
