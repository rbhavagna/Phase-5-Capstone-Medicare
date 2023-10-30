package com.Medipro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Medipro.enitity.Cart;
import com.Medipro.service.CartService;


@RestController
public class CartController {

	@Autowired
	private CartService cartSer;
	
	@GetMapping({"/addCart/{productId}"})
	public Cart addToCart(@PathVariable(name="productId") long productId) {
		return cartSer.addToCart(productId);
	}
	
	@GetMapping({"/getCartDetails"})
	public List<Cart> getCartDetails(){
		return cartSer.getCartDetails();
	}
	
	@DeleteMapping({"/deleteCart/{cartId}"})
	public void deleteCart(@PathVariable(name="cartId") long cartId) {
		cartSer.deleteCart(cartId);
	}
}
