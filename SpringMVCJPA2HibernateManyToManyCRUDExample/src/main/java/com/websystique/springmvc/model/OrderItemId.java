package com.websystique.springmvc.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
@Embeddable
public class OrderItemId implements Serializable{
	
	private OrderHeader orderHead;
	private Item item;
	
	public OrderItemId() {
	
	}
	@ManyToOne(cascade=CascadeType.ALL)
	public OrderHeader getOrderHead() {
		return orderHead;
	}
	public void setOrderHead(OrderHeader orderHead) {
		this.orderHead = orderHead;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	}

