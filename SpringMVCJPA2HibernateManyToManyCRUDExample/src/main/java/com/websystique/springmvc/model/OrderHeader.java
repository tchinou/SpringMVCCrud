package com.websystique.springmvc.model;

import java.math.BigDecimal;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="ORDERHEADER")
public class OrderHeader {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_order")
	private Integer id;
	@Temporal(TemporalType.DATE)
	@Column(name="date_order")
	private Date date;
	@Column (name="price_order")
	private BigDecimal price;
	@OneToMany(mappedBy="idOrderItem.orderHead", cascade=CascadeType.ALL)
	private Set <OrderItem> orderItems;
	//private User user;
	public OrderHeader() {
	
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	//@ManyToOne
	////@JoinColumn(name="user_id", nullable=false)
	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
