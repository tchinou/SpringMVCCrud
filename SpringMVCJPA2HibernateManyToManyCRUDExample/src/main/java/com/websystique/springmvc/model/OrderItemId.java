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
	public OrderItemId(OrderHeader orderHead, Item item) {

		this.orderHead = orderHead;
		this.item = item;
		
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((orderHead == null) ? 0 : orderHead.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemId other = (OrderItemId) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (orderHead == null) {
			if (other.orderHead != null)
				return false;
		} else if (!orderHead.equals(other.orderHead))
			return false;
		return true;
	}
	
	}

