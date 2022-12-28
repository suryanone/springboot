package com.wide.spring_boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_tbl")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;
	private String code;
	private String name;
	private String type;
	private double price;
	
	public Product(String code, String name, String type, double price) {
		this.code = code;
		this.name = name;
		this.type = type;
		this.price = price;
	}
	
	public Product() {
		
	}
	public Product(int productId, String code, String name, String type, double price) {
		this.productId = productId;
		this.code = code;
		this.name = name;
		this.type = type;
		this.price = price;
	}
	public int getProductId() {
		return productId;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public double getPrice() {
		return price;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String info() {
		return ("Code : "+this.code+", Name : "+this.name+", Type : "+this.type+", Price : "+
				this.price);
	}
}
