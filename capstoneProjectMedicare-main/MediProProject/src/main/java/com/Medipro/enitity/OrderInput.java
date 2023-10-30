package com.Medipro.enitity;



import java.util.Date;
import java.util.List;

import lombok.Data;

public class OrderInput {

	private String fullName;
	private String fullAddress;
	private String contactNumber;
	private List<ProductQuantity> productQuantity;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public List<ProductQuantity> getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(List<ProductQuantity> productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	
}
