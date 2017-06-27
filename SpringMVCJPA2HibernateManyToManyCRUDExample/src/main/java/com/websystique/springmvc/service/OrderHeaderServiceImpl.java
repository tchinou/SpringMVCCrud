package com.websystique.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.dao.OrderHeaderDao;
import com.websystique.springmvc.model.OrderHeader;

@Service("orderHeaderService")
@Transactional
public class OrderHeaderServiceImpl implements OrderHeaderService{
	
	@Autowired
	OrderHeaderDao orderHeaderDao;
	
	@Override
	public OrderHeader findById(Integer id) {
		return orderHeaderDao.findById(id);
	}

	@Override
	public void save(OrderHeader orderHeader) {
		orderHeaderDao.save(orderHeader);
		
	}

	@Override
	public void update(OrderHeader orderHeader) {
		orderHeaderDao.update(orderHeader);
		
	}

	@Override
	public void deleteById(int id) {
		orderHeaderDao.deleteById(id);
		
	}

	@Override
	public List<OrderHeader> findAllOrders() {
		return orderHeaderDao.findAllOrders();
	}

	@Override
	public List<OrderHeader> findAllOrders(Integer id) {
		return orderHeaderDao.findAllOrders(id);
	}

}
