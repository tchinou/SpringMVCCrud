package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.OrderItem;

public interface OrderItemService {
	OrderItem findById(int id);
	
	OrderItem findByClass();
	
	OrderItem findByOrderId(int id);

	void save(OrderItem orderItem);
	
	void update(OrderItem orderItem);
	
	void deleteById(int id);
	
	List<OrderItem> findAllOrderItems(int id);

}
