package com.wide.spring_boot.dao;

import org.springframework.data.repository.CrudRepository;

import com.wide.spring_boot.model.OrderItem;

public interface OrderItemDao extends CrudRepository<OrderItem, Integer> {

}
