package com.Medipro.enitity;



import javax.persistence.*;
import java.util.Set;
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long ProductId;
	private String productName;
	private String productCategory;
	@Column(length=2000)
	private String productDescription;
	private double productPrice;
	private double discountPrice;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "product_images",
	
	         joinColumns= {
	        		 @JoinColumn(name="product_id")
	         },
	         inverseJoinColumns= {
	        		 @JoinColumn(name="image_id")
	         }
			
	)
	private Set<ImageEn> productImage;
	private int active = 0;
	
	
	
	

	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Set<ImageEn> getProductImage() {
		return productImage;
	}
	public void setProductImage(Set<ImageEn> productImage) {
		this.productImage = productImage;
	}
	public long getProductId() {
		return ProductId;
	}
	public void setProductId(long productId) {
		ProductId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	
}
