package com.example.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.TaxProducts;
import com.example.repository.TaxProductRepository;

@Component
public class TaxProductsDao {
	private static final Logger log = LoggerFactory.getLogger(TaxProductsDao.class);

	@Autowired
	private TaxProductRepository taxRepository;
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public String addProducts(TaxProducts products) {
		String s = "Successfully added";
		try {
			log.info("Received Product details as:" + products);
			taxRepository.saveAndFlush(products);

		} catch (Exception ex) {
			// log.error("Error occured while processing:" + ex.printStackTrace());
			ex.printStackTrace();
			s = "Product addition failed";

		}
		return s;

	}

	@Transactional
	public String updateAmounts(TaxProducts product) {
		log.info("Product Code in the request:" + product.getProductCode());
		TaxProducts taxProduct = taxRepository.findByProductCode(product.getProductCode());
		log.info("Fetched entity:" + taxProduct);
		// taxProduct.setSerial(taxProduct.getSerial());
		if (Optional.ofNullable(product.getActualAmount()).isPresent()) {
			taxProduct.setActualAmount(product.getActualAmount());
		}
		if (Optional.ofNullable(product.getDeclaredAmount()).isPresent()) {
			taxProduct.setDeclaredAmount(product.getDeclaredAmount());
		}

		// taxProduct.setDeclaredAmount(product.getDeclaredAmount());
		taxRepository.saveAndFlush(taxProduct);
		// em.merge(product);
		return "Record updated successfully";

	}

	public String deleteProductInformation(TaxProducts product) {
		// TODO Auto-generated method stub
		log.info("Product Code deletion request for product:" + product.getProductCode());
		TaxProducts taxProduct = taxRepository.findByProductCode(product.getProductCode());
		log.info("Fetched entity:" + taxProduct);
		if (Optional.ofNullable(taxProduct.getSerial()).isPresent()) {
			taxRepository.delete(taxProduct);
			return "Product deleted successfully!";
		} else
			return "Product details are not present in system!";
	}

	public List<TaxProducts> getAllActiveProducts() {
		// return taxRepository.findProductCode();
		return taxRepository.findAll();
	}

	public TaxProducts getProductDetails(String productName) {
		return taxRepository.findByProductCode(productName);
	}
	/*
	 * public Object copyProperties(Object parent,Object anotherParent) { for
	 * (Map.Entry<String, Object> e : PropertyUtils.describe(parent).entrySet()) {
	 * if (e.getValue() != null && !e.getKey().equals("class")) {
	 * PropertyUtils.setProperty(anotherParent, e.getKey(), e.getValue()); } }
	 */

}
