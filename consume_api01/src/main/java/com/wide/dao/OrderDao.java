package com.wide.dao;

import java.util.List;

import com.wide.model.Order;

public interface OrderDao {
	void save (Order order) throws RepositoryException;
	List<Order> orderList() throws RepositoryException;
	Order findOrderById(int orderId) throws RepositoryException;
}
