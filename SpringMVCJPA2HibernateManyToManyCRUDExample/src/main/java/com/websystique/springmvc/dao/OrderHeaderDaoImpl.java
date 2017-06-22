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

	@SuppressWarnings("unchecked")
	public List<OrderHeader> findAllOrders() {
		List <OrderHeader> ordersHeader = getEntityManager()
				.createQuery("SELECT o FROM OrderHeader o ORDER BY o.id ASC")
				.getResultList();
		return ordersHeader;
	}


	@Override
	public List<OrderHeader> findAllOrders(int id) {
		List<OrderHeader> ordersHeaderAll = getEntityManager()
				.createQuery("SELECT o FROM OrderHeader o WHERE o.id LIKE :id")
				.setParameter("id", id)
				.getResultList();
		return ordersHeaderAll;
	}

}
