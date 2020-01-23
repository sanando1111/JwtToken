package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.TaxProducts;

@Repository
public interface TaxProductRepository extends JpaRepository<TaxProducts, Integer> {

	TaxProducts findByProductCode(String productCode);

	@Query("SELECT t.productCode FROM TaxProducts t") 
	List<String> findProductCode();

	/*
	 * @Modifying
	 * 
	 * @Query("update User u set u.active = false where u.lastLoginDate < :date")
	 * void deactivateUsersNotLoggedInSince(@Param("date") LocalDate date);
	 */

}
