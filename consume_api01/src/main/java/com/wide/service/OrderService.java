package com.wide.service;

import java.util.List;

import com.wide.dao.RepositoryException;
import com.wide.model.Order;

public interface OrderService {
	public void save (Order order) throws RepositoryException;
	public List<Order> orderList() throws RepositoryException;
	public Order findOrderById(int orderId) throws RepositoryException;
}
