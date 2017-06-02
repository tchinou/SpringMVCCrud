package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Item;


public interface ItemDao {
	
	Item findById(int id);
		
	void save(Item item);
	
	void update(Item item);
	
	void deleteById(int id);
	
	List<Item> findAllItems();
	
	List<Item> sortItemsByName();
	
	List <Item> sortItemByPriceAscDesc(String field);
}
