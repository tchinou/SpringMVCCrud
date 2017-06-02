package com.websystique.springmvc.service;

import javax.servlet.http.HttpServletRequest;

import com.websystique.springmvc.model.Cart;


public class CartUtil {
	 public static Cart getCartInSession(HttpServletRequest request) {
		 
	        // Get Cart from Session.
	        Cart cartInfo = (Cart) request.getSession().getAttribute("myCart");
	        
	        // If null, create it.
	        if (cartInfo == null) {
	            cartInfo = new Cart();
	            
	            // And store to Session.
	            request.getSession().setAttribute("myCart", cartInfo);
	        }
	 
	        return cartInfo;
	    }
}
