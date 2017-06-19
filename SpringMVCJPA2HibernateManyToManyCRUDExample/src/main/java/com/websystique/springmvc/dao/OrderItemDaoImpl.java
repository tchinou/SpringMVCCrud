package com.websystique.springmvc.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.OrderItem;

@Repository("orderItemDao")
public class OrderItemDaoImpl extends AbstractDao<Integer, OrderItem> implements OrderItemDao{

	@Override
	public OrderItem findById(int id) {
		return null;
	}

	@Override
	public void save(OrderItem orderItem) {
		persist(orderItem);
	}

	@Override
	public void update(OrderItem orderItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderItem> findAllOrderItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
