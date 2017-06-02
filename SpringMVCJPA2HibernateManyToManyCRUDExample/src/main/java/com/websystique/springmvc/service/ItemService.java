package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Item;

public interface ItemService {
	
	Item findById(int id);
	
	void saveItem(Item item);
	
	void updateItem(Item item);
	
	void deleteById(int id);
	
	List<Item> findAllItems();
	
	List<Item> sortItemsByName();
	
	List <Item> sortItemByPriceAscDesc(String field);

}
