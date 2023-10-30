package com.Medipro.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Medipro.Config.JwtRequestFilter;
import com.Medipro.enitity.Cart;
import com.Medipro.enitity.Product;
import com.Medipro.enitity.User;
import com.Medipro.repository.CartRepository;
import com.Medipro.repository.ProductRepository;
import com.Medipro.repository.UserDao;



@Service
public class CartService {

	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private UserDao userRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	public Cart addToCart(long productId) {
		Product product = productRepo.findById(productId).get();
		String username = JwtRequestFilter.CURRENT_USER;
		User user = null;
		
		if(username != null) {
			user = userRepo.findById(username).get();
		}
		
		List<Cart> cartList = cartRepo.findByUser(user);
		List<Cart>filterdList = cartList.stream().filter(x -> x.getProduct().getProductId() == productId).collect(Collectors.toList());
		
		if(filterdList.size()>0) {
			return null;
		}
		
		if(product != null && user != null) {
			Cart cart = new Cart(product, user);
			return cartRepo.save(cart);
		}
		return null;
	}
	
	public List<Cart> getCartDetails(){
		String username = JwtRequestFilter.CURRENT_USER;
		User user = userRepo.findById(username).get();
		return cartRepo.findByUser(user);
	}
	
	public void deleteCart(long cartId) {
		cartRepo.deleteById(cartId);
	}
}
