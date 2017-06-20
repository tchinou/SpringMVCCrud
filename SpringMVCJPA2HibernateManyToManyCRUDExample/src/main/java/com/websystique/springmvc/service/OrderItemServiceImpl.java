package com.websystique.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.dao.OrderItemDao;
import com.websystique.springmvc.model.OrderItem;
import com.websystique.springmvc.model.OrderItemId;

@Service("orderItemService")
@Transactional
public class OrderItemServiceImpl implements OrderItemService{
	
	@Autowired
	OrderItemDao orderItemDao;
	
	@Override
	public OrderItem findById(int id) {
		return orderItemDao.findById(id);
	}

	@Override
	public void save(OrderItem orderItem) {
		 orderItemDao.save(orderItem);
		
	}

	@Override
	public void update(OrderItem orderItem) {
		orderItemDao.update(orderItem);
		
	}

	@Override
	public void deleteById(int id) {
		orderItemDao.deleteById(id);
		
	}

	@Override
	public List<OrderItem> findAllOrderItems(int id) {
		return orderItemDao.findAllOrderItems(id);
	}

	@Override
	public OrderItem findByOrderId(int id) {
		return orderItemDao.findByOrderId(id);
	}

}
