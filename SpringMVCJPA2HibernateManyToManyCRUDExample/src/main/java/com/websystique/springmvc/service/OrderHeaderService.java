package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.OrderHeader;


public interface OrderHeaderService {
	OrderHeader findById(int id);
	
	void save(OrderHeader orderHeader);
	
	void update(OrderHeader orderHeader);
	
	void deleteById(int id);
	
	List<OrderHeader> findAllOrders();

}