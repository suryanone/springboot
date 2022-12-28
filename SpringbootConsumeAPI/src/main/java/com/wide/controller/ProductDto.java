package com.wide.controller;

public class ProductDto {
	private int productDtoId;
	private String name;
	private double price;
	private int quantity;
	private double totalPrice;
	private double totalOrder = 0;
	
	public ProductDto(int productDtoId, String name, double price, int quantity, double totalPrice) {
		this.productDtoId = productDtoId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	public ProductDto() {
	}

	public int getProductDtoId() {
		return productDtoId;
	}
	public void setProductDtoId(int productDtoId) {
		this.productDtoId = productDtoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTotalOrder() {
		return totalOrder;
	}
	public void setTotalOrder(double totalOrder) {
		this.totalOrder = totalOrder;
	}
}
