package com.websystique.springmvc.model;


import java.awt.Image;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="items")
public class Item{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_item")
	private int id;
	//@NotEmpty
	@Column(name="name_item")
	private String name;
	//@NotNull
	@Column(name="price_item")
	private BigDecimal price;
	//@NotEmpty
	@Column(name="description_item")
	private String description;
	//@Column(name="image_item")
   	//private Image img;
	@OneToMany(mappedBy="idOrderItem.item", cascade=CascadeType.ALL)
	private Set <OrderItem> OrdItems; 
//	
	
	public Set<OrderItem> getOrdItems() {
		return OrdItems;
	}
//
	public void setOrdItems(Set<OrderItem> ordItems) {
		OrdItems = ordItems;
	}

	public Item(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	
}