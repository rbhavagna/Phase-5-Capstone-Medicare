package com.Medipro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Medipro.enitity.OrderInput;
import com.Medipro.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderSer;
	
	@PostMapping({"/placeOrder"})
	public void placeOrder(@RequestBody OrderInput orderInput) {
		orderSer.placeOrder(orderInput);
	}
}
