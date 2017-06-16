package com.websystique.springmvc.service.mail;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.websystique.springmvc.model.ProductOrder;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	MailService mailService;

	public void sendOrderConfirmation(ProductOrder productOrder) {
		mailService.sendEmail(productOrder);
		
	}
	
}

