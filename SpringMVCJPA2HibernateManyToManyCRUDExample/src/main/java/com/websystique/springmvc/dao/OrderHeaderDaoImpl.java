package com.websystique.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.OrderHeader;

@Repository("orderHeaderDao")
public class OrderHeaderDaoImpl extends AbstractDao<Integer, OrderHeader>implements OrderHeaderDao{

	@Override
	public OrderHeader findById(int id) {
		OrderHeader orderHeader = getByKey(id); 
		return orderHeader;
	}

	
	public void save(OrderHeader orderHeader) {
		persist(orderHeader);
		
	}

	@Override
	public void update(OrderHeader orderHeader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderHeader> findAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
