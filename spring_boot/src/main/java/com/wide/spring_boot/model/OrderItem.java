package com.wide.spring_boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="order_item_tbl")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_item_id")
	private int id;
	@Column(name="price")
	private double price;
	@Column(name="quantity")
	private int quantity;
	@Column(name="product_id_fk")
	private int productIdFk;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id_fk", referencedColumnName = "order_id")
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
