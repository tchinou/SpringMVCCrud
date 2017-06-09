package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.OrderItem;



public interface OrderItemDao {
	
	OrderItem findById(int id);
	
	void save(OrderItem orderItem);
	
	void update(OrderItem orderItem);
	
	void deleteById(int id);
	
	List<OrderItem> findAllOrderItems();
}
