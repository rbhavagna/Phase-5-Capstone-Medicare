package com.Medipro.enitity;



import java.util.Date;

import javax.persistence.*;


@Entity
@Table

public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
    private String userFullName;
	private String orderAddress;
	private String contactNumber;
	private String orderStatus;
	private double orderAmount;
	
	@OneToOne
	private Product product;
	
	@OneToOne
	private User user;
	
	
	
	
	
	
	public OrderDetails(String userFullName, String orderAddress, String contactNumber, String orderStatus,
			double orderAmount, Product product, User user) {
		super();
		this.userFullName = userFullName;
		this.orderAddress = orderAddress;
		this.contactNumber = contactNumber;
		this.orderStatus = orderStatus;
		this.orderAmount = orderAmount;
		this.product = product;
		this.user = user;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	
	
	
	
	
}
