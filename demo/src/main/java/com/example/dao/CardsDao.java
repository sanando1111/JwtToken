package com.example.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Cards;
import com.example.repository.CardsRepository;

@Component
public class CardsDao {
	private static final Logger log = LoggerFactory.getLogger(CardsDao.class);

	@Autowired
	CardsRepository cardsRepository;

	public List<Cards> getAllCardDetails() {
		log.info("Fetching all cards details dao");
		return cardsRepository.findAll();

	}

	
	public List<Cards> getActiveCardDetails() {
		log.info("Fetching active cards details dao");
		return cardsRepository.findByActive("Y");

	}
}
