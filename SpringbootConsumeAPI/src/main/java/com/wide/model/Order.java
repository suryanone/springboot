package com.wide.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Order {
	private int id;
	private Date createdAt;

	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	
	public Order(int id, Date createdAt, List<OrderItem> orderItems) {
		this.id = id;
		this.createdAt = createdAt;
		this.orderItems = orderItems;
	}

	public Order(int id) {
		this.id = id;
	}

	public Order() {
	}

	public double totalPrice() {
		double total = 0;
		for (int i=0; i < orderItems.size(); i++) {
			total += orderItems.get(i).totalPrice();
		}
		return total;
	}
	
	public void addItem(OrderItem orderItem) {
			this.orderItems.add(orderItem);
		}
	@JsonManagedReference
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
}
