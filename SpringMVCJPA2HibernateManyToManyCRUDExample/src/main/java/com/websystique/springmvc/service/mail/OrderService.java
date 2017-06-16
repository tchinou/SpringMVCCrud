package com.websystique.springmvc.service.mail;

import com.websystique.springmvc.model.ProductOrder;

public interface OrderService {

	public void sendOrderConfirmation(ProductOrder productOrder);
}
