package com.websystique.springmvc.model;

import java.math.BigDecimal;


public class ItemInfo {
	private double price;
	private String description;
	private int id;
	
	public ItemInfo(Item p){
		this.description=p.getDescription();		
		this.id=p.getId();
	}
}
