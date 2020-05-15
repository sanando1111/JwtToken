package com.example.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.TaxProductsDao;
import com.example.model.TaxProducts;

@Service
public class TaxProductsService {

	private static final Logger log = LoggerFactory.getLogger(TaxProductsService.class);

	@Autowired
	TaxProductsDao taxproductDao;

	public String addProducts(TaxProducts products) {
		log.info("Calling the add product service");
		return taxproductDao.addProducts(products);
	}

	public String updateProductAmount(TaxProducts products) {
		log.info("Calling the update product service");
		return taxproductDao.updateAmounts(products);
	}

	public String deleteProduct(TaxProducts product) {
		// TODO Auto-generated method stub
		log.info("Calling the delete product service");
		return taxproductDao.deleteProductInformation(product);
	}

	public List<String> getActiveTaxProducts() {
		log.info("Calling the active tax products service");
		List<TaxProducts> productList = taxproductDao.getAllActiveProducts();
		List<String> listProductCode = productList.stream().map(e -> e.getProductCode()).collect(Collectors.toList());
		// Map<Integer,String>
		// productMap=productList.stream().collect(Collectors.toMap(TaxProducts::getSerial,
		// TaxProducts::getProductCode));
		return listProductCode;
	}

	public TaxProducts getProductDetails(String productName) {
		return taxproductDao.getProductDetails(productName);
	}
}
