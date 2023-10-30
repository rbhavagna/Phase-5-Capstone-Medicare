package com.Medipro.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Medipro.*;
import com.Medipro.Config.JwtRequestFilter;
import com.Medipro.enitity.Cart;
import com.Medipro.enitity.Product;
import com.Medipro.enitity.User;
import com.Medipro.repository.CartRepository;
import com.Medipro.repository.ProductRepository;
import com.Medipro.repository.UserDao;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private UserDao userDao;
	
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}
	public List<Product> getAllProduct(String searchkey){
		if(searchkey.equals("")) {
			return productRepo.findAll();
		}else {
			return productRepo.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchkey, searchkey);
		}
		
				
	}
	public List<Product> getAllProductBasedOnCatogary(String category) {
		return productRepo.findAllByCategory(category);
	}
	
//	public Optional<Product> getProductById(long productId) {
//		return productRepo.findById(productId);
//		
//	}
	
	public Product getProductById(long productId) {
		return productRepo.findById(productId).get();
	}
	
	public Product ActiveProduct(long productId) {
		if(productRepo.findById(productId).isPresent()) {
			Product fr = productRepo.findById(productId).get();
			fr.setActive(1);
			return productRepo.save(fr);
		}else {
			return null;
		}
		
	}
	
	public void deleteProduct(long productId) {
		 productRepo.deleteById(productId);
	}
	
	public List<Product> getProductDetail(boolean isSingleProduct,long productId){
		
	if(isSingleProduct && productId != 0) {
		List<Product> list = new ArrayList<>();
		Product product = productRepo.findById(productId).get();
		list.add(product);
		return list;
	}
	else {
		
		String username = JwtRequestFilter.CURRENT_USER;
		User user = userDao.findById(username).get();
		List<Cart> cart = cartRepo.findByUser(user);
		
		return cart.stream().map(x -> x.getProduct()).collect(Collectors.toList());
	}
	
	}
	
}
