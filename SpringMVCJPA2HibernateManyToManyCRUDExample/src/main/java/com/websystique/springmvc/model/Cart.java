package com.websystique.springmvc.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Cart {
	
	private final List <ItemInCart> products = new ArrayList<ItemInCart>();
	private Map<String, ItemInCart> map;
	private int quantity;

	public Cart(){
		map = new HashMap<String, ItemInCart>();
		System.out.println("New Cart created");
	}
	public Integer getQuantityCart(){
		int qte = 0;
		
		return null;
	}
	public Integer numberOfProducts(List<ItemInCart> itemsCart){
		return itemsCart.size();
		
	}
	public List<ItemInCart> getProducts() {
		return products;
	}
	public Collection<ItemInCart> getCartDetails(){
		return map.values();
	}
	public void addProduct(Item p,int quantity){
		ItemInCart pl=getPl(p.getId());
		if(pl==null){
			System.out.println("pl is null");
			ItemInCart ppl=new ItemInCart();
			ppl.setId(p.getId());
			ppl.setName(p.getName());
			ppl.setQuantity(quantity);
			ppl.setPrice(p.getPrice());
			ppl.getSubtotal();
			products.add(ppl);
			
		}
		else{
			pl.setQuantity(pl.getQuantity()+quantity);
		}
		
	}
	public Integer getTotalQuantity(List<ItemInCart> listItems){

		Integer quantity=0;
		
		for(int i=0; i< listItems.size(); i++){
			
			quantity = quantity + listItems.get(i).getQuantity();

		}
		return quantity;
	}
	public Integer getNumberDifferentItems(List<ItemInCart> listItems){
		System.out.println(listItems.size());
		return listItems.size();
	}
	public void addQuantity(int quantity){
		
		this.quantity=this.quantity+quantity;
	}
	public void deleteProduct(Item p){
		
		ItemInCart pl=getPl(p.getId());
		if(pl!=null){
			System.out.println("remove item");
			products.remove(pl);
		
		}
	}
	
	public ItemInCart getPl(long id){
		for(ItemInCart pl:products){
			if(id==pl.getId())
				return pl;
		}
		return null;
	}
	public BigDecimal getCartPrice() {
		BigDecimal price = null;
		 Iterator<ItemInCart> iterator = getProducts().iterator();
		 while(iterator.hasNext()){
			 System.out.println();
		    //price.add(iterator.next().getPrice());
		    }
		    return price;
		  }
}
