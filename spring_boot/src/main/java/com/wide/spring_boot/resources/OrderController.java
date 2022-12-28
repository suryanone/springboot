package com.wide.spring_boot.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wide.spring_boot.dao.OrderDao;
import com.wide.spring_boot.dao.OrderItemDao;
import com.wide.spring_boot.model.Order;
import com.wide.spring_boot.model.OrderItem;

@RestController
public class OrderController {
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderItemDao orderItemDao;

	@PostMapping("/orders")
	public Order saveOrder(@RequestBody List<OrderItem> orderItems) {
		Order order = new Order();
		for(OrderItem orderItem: orderItems) {
			order.addItem(orderItem);
			orderItem.setOrder(order);
		}
		return orderDao.save(order);
	}
	
	@DeleteMapping("/orders/{orderId}")
	public void deleteOrder(@PathVariable("orderId") int orderId) {
		orderDao.deleteById(orderId);
	}
	
	@GetMapping("/orders")
	public List<Order> getListOrders() {
		return orderDao.findAll();
	}
	
	@GetMapping("/orders/{orderId}")
	public Order findOrderById(@PathVariable("orderId") int orderId) {
		return orderDao.findById(orderId);
	}
	
	@PutMapping("/orders/{orderId}")
	public Order updateOrderById(@PathVariable("orderId") int orderId, @RequestBody List<OrderItem> orderItems) {
		Order order = orderDao.findById(orderId);
		List<Integer> orderItemIds = new ArrayList<Integer>();
		for(OrderItem orderItem: order.getOrderItems()) {
			orderItemIds.add(orderItem.getId());
		}
		//can;t work if deleteAllById in middle ?? dont know why
//		orderItemDao.deleteAllById(orderItemIds);
		for(OrderItem orderItem: orderItems) {
			orderItem.setOrder(order);
		}
		order.setOrderItems(orderItems);
		
		//deleteAllById can work if before saving the change
		orderItemDao.deleteAllById(orderItemIds);
		return orderDao.save(order);
	}
}
