package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CardsDao;
import com.example.model.Cards;

@Service
public class CardsService {

	@Autowired
	CardsDao cardsDao;

	public List<Cards> getAllCardDetails() {

		return cardsDao.getAllCardDetails();
	}

	public List<Cards> getActiveCardDetails() {

		return cardsDao.getActiveCardDetails();
	}
}
