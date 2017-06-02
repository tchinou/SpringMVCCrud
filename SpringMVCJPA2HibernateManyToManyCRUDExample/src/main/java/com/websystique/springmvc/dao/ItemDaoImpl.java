package com.websystique.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Item;
@Repository("itemDao")
public class ItemDaoImpl extends AbstractDao<Integer, Item> implements ItemDao{

	@Override
	public Item findById(int id) {
		Item item = getByKey(id);
		return item;
	}

	@Override
	public void save(Item item) {
		persist(item);
		
	}

	@Override
	public void update(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		Item item = (Item) getEntityManager().
				createQuery("SELECT i FROM Item i WHERE i.id LIKE :id")
				.setParameter("id", id)
				.getSingleResult();
		delete(item);		
	}

	@SuppressWarnings("unchecked")
	public List<Item> findAllItems() {
		List<Item> items = getEntityManager()
				.createQuery("SELECT i FROM Item i ORDER BY i.description ASC")
				.getResultList();
		return items;
	}


	@Override
	public List<Item> sortItemsByName() {
		List <Item> items = getEntityManager()
				.createQuery("SELECT i FROM Item i ORDER BY i.name ASC")
				.getResultList();
		return items;
	}


	@Override
	public List<Item> sortItemByPriceAscDesc(String field) {
		List <Item> items = getEntityManager()
				.createQuery("SELECT i FROM Item i ORDER BY i.price"+ " "+field)
				.getResultList();
		return items;
	}



}
