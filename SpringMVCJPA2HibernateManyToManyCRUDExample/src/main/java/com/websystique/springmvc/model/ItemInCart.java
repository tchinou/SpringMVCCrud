package com.websystique.springmvc.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemInCart {
	private ItemInfo productInfo;
	
	 private String name;
	 private long id;
	 private int quantity;
	 private BigDecimal price;
	 private BigDecimal subtotal;
	 List <Item> items = new ArrayList<Item>();
	 public ItemInCart(){
		 
	 }

	public ItemInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ItemInfo productInfo) {
		this.productInfo = productInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSubtotal() {
		return price.multiply(new BigDecimal(quantity));
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	public BigDecimal getSubtotalFromOrderTable(BigDecimal price, int quantity) {
		return price.multiply(new BigDecimal(quantity));
	}

}
