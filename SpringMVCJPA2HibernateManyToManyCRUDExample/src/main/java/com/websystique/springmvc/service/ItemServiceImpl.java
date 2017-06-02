package com.websystique.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.dao.ItemDao;
import com.websystique.springmvc.model.Item;
import com.websystique.springmvc.model.User;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemDao iDao;
	@Override
	public Item findById(int id) {
		return iDao.findById(id);
	}

	

	@Override
	public void deleteById(int id) {
		iDao.deleteById(id);
		
	}

	@Override
	public List<Item> findAllItems() {
		return iDao.findAllItems();
	}

	@Override
	public void saveItem(Item item) {
		iDao.save(item);
	}

	@Override
	public void updateItem(Item item) {
		Item entity = iDao.findById(item.getId());
		if(entity!=null){
			
			entity.setName(item.getName());
			entity.setPrice(item.getPrice());
			entity.setDescription(item.getDescription());
			
		}
		
	}

	public List<Item> sortItemsByName() {
		return iDao.sortItemsByName();
	}

	@Override
	public List<Item> sortItemByPriceAscDesc(String field) {
		return iDao.sortItemByPriceAscDesc(field);
	}

}
