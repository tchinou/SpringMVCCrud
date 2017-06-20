package com.websystique.springmvc.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.OrderHeader;
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
	public List<OrderItem> findAllOrderItems(int id) {
		List <OrderItem> orderItem = (List<OrderItem>) getEntityManager()
				.createQuery("SELECT o FROM OrderItem o WHERE o.idOrderItem.item.id LIKE :id")
				.setParameter("id", id)
				.getResultList();
		return orderItem;
	}

	@Override
	public OrderItem findByOrderId(int id) {
		OrderItem orderItem = (OrderItem) getEntityManager()
				.createQuery("SELECT o FROM OrderItem o WHERE o.idOrderItem.item.id LIKE :id")
				.setParameter("id", id)
				.getResultList();
		return orderItem;
	}

	@Override
	public List<OrderItem> findAllWithOrderHeaderId(OrderHeader orderHeader) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
