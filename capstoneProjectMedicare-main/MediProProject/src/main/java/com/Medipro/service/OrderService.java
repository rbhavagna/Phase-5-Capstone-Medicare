package com.Medipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Medipro.Config.JwtRequestFilter;
import com.Medipro.enitity.Cart;
import com.Medipro.enitity.OrderDetails;
import com.Medipro.enitity.OrderInput;
import com.Medipro.enitity.Product;
import com.Medipro.enitity.ProductQuantity;
import com.Medipro.enitity.User;
import com.Medipro.repository.CartRepository;
import com.Medipro.repository.OrderRepository;
import com.Medipro.repository.ProductRepository;
import com.Medipro.repository.UserDao;

@Service
public class OrderService {
	
	private static final String ORDER_PLACED = "Placed";

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartRepository cartRepo;
	
	public void placeOrder(OrderInput orderInput) {
		List<ProductQuantity> productQuantity=orderInput.getProductQuantity();
		
		for(ProductQuantity p:productQuantity) {
			Product product = productRepo.findById(p.getProductId()).get();
			String currentUser = JwtRequestFilter.CURRENT_USER;
			User user = userDao.findById(currentUser).get();
			OrderDetails orderDetails = new OrderDetails(
				
					orderInput.getFullName(),
					orderInput.getFullAddress(),
					orderInput.getContactNumber(),
					ORDER_PLACED,
					product.getDiscountPrice()*p.getQuantity(),
					product,
					user
							
			);
		
			orderRepo.save(orderDetails);
		}
	}
}
