package com.wide.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class OrderItem {
	
	private int id;
	private double price;
	private int quantity;
	private int productIdFk;

	private Order order;

	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderItem(double price, int quantity, int productIdFk) {
		this.productIdFk = productIdFk;
		this.price = price;
		this.quantity = quantity;
	}
	public OrderItem() {
		
	}
	public int getId() {
		return id;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getProductIdFk() {
		return productIdFk;
	}
	public void setProductIdFk(int productIdFk) {
		this.productIdFk = productIdFk;
	}
	@JsonBackReference
	public Order getOrder() {
		return order;
	}
	public double totalPrice() {
		return quantity * price;
	}
	public String info() {
		return ("Price : "+ this.price+", Qty : "+this.quantity+"\n\tTotal Price : "+this.totalPrice());
	}
}
