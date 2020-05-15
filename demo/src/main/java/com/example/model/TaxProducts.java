package com.example.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tax_products",uniqueConstraints={@UniqueConstraint(columnNames={"product_code"})})
@SelectBeforeUpdate
@DynamicUpdate
public class TaxProducts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "serial",updatable = false,insertable = false)
	private Integer serial;
	@NotEmpty
	@NotNull
	@Column(name = "product_code",updatable = false,insertable = true)
	private String productCode;
	@Column(name = "declared_amount",insertable = true,updatable = true)
	private BigDecimal declaredAmount;
	@Column(name = "actual_amount",insertable = true,updatable = true)
	private BigDecimal actualAmount;

	@Override
	public String toString() {
		return "TaxProducts [serial=" + serial + ", productCode=" + productCode + ", declaredAmount=" + declaredAmount
				+ ", actualAmount=" + actualAmount + ", getSerial()=" + getSerial() + ", getProductCode()="
				+ getProductCode() + ", getDeclaredAmount()=" + getDeclaredAmount() + ", getActualAmount()="
				+ getActualAmount() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getDeclaredAmount() {
		return declaredAmount;
	}

	public void setDeclaredAmount(BigDecimal declaredAmount) {
		this.declaredAmount = declaredAmount;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

}
