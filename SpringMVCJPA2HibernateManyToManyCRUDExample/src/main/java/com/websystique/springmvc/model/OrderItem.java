package com.websystique.springmvc.model;

import java.io.Serializable;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="Order_Item")
@AssociationOverrides({
	@AssociationOverride(name="idOrderItem.item", 
			joinColumns=@JoinColumn(name="item_id")),
	@AssociationOverride(name="idOrderItem.orderHead", 
	joinColumns=@JoinColumn(name="order_id"))
})
public class OrderItem {
	private OrderItemId idOrderItem = new OrderItemId();
	//additional fields
	private Integer nbItem ;
	
	public OrderItem(){

	}
	@Column(name="NOMBRE_ITEM")
	public Integer getNbItem() {
		return nbItem;
	}
	public void setNbItem(Integer nbItem) {
		this.nbItem = nbItem;
	}

	@EmbeddedId
	public OrderItemId getIdOrderItem() {
		return idOrderItem;
	}
	
	public void setIdOrderItem(OrderItemId idOrderItem) {
		this.idOrderItem = idOrderItem;
	}

	@Transient
	public OrderHeader getOrderHeader() {
		return getIdOrderItem().getOrderHead();
	}

	public void setOrderHeader(OrderHeader orderHeader) {
		getIdOrderItem().setOrderHead(orderHeader);
	}
	@Transient
	public Item getItem() {
		return getIdOrderItem().getItem();
	}

	public void setItem(Item item) {
		getIdOrderItem().setItem(item);
	}
	
	

}
