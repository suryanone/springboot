package com.wide.spring_boot.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wide.spring_boot.model.Order;
import com.wide.spring_boot.model.OrderItem;

public interface OrderDao extends CrudRepository<Order, Integer> {
	List<Order> findAll();
	Order findById(int orderId);
}
