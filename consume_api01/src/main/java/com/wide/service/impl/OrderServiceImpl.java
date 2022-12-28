package com.wide.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wide.dao.OrderDao;
import com.wide.dao.RepositoryException;
import com.wide.model.Order;
import com.wide.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDao orderDao;

	@Override
	public void save(Order order) throws RepositoryException {
		orderDao.save(order);
	}

	@Override
	public List<Order> orderList() throws RepositoryException {
		return orderDao.orderList();
	}

	@Override
	public Order findOrderById(int orderId) throws RepositoryException {
		return orderDao.findOrderById(orderId);
	}

}
