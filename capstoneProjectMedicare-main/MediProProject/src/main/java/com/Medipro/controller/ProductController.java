package com.Medipro.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Medipro.enitity.ImageEn;
import com.Medipro.enitity.Product;
import com.Medipro.repository.ProductRepository;
import com.Medipro.service.ProductService;




@RestController
public class ProductController {

	@Autowired
	private ProductService productSer;
	
	@Autowired
	private ProductRepository productRepo;
	
	
	@PreAuthorize("hasRole('Admin')")
	@PostMapping(value ={"/addProducts"},consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Product addNewProduct(
			@RequestPart("product") Product product,
			@RequestPart("imageFile") MultipartFile[] file
			) {
		
		try {
			
			Set<ImageEn> images = uploadImage(file);
			product.setProductImage(images);
			
            return productSer.addProduct(product);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	@PutMapping({"/enable/{productId}"})
	public ResponseEntity<Object> activeProduct(@PathVariable long productId){
		Product obj = productSer.ActiveProduct(productId);
		if(obj!=null) {
			return new ResponseEntity<Object>(obj,HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>("No product Found",HttpStatus.NOT_FOUND);
		}
	}
	
	public Set<ImageEn> uploadImage(MultipartFile[] multipartFiles) throws IOException {
		Set<ImageEn> imageEn = new HashSet<>();
		for(MultipartFile file:multipartFiles) {
			ImageEn image = new ImageEn(
				file.getOriginalFilename(),
				file.getContentType(),
				file.getBytes()
					
		);
		
			imageEn.add(image);					
		}
		return imageEn;
		
	}
	
	
	
//	@PreAuthorize("hasRole('Admin')")
	@GetMapping({"/getAllProduct"})
	public List<Product> getAllProducts(@RequestParam(defaultValue="")String searchkey){
		return productSer.getAllProduct(searchkey);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/getProduct/categorize/{category}")
	public List<Product> getAllProductByCategory(@PathVariable("productCategory")String productCategory){
		return productSer.getAllProductBasedOnCatogary(productCategory);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@PutMapping({"/getProduct/{productId}"})
	public ResponseEntity<Product> updateEmp(@PathVariable long productId,@RequestBody Product product){
	Product pr = productRepo.getById(productId);
	pr.setProductName(product.getProductName());
	pr.setProductCategory(product.getProductCategory());
	pr.setProductPrice(product.getProductPrice());
	pr.setDiscountPrice(product.getDiscountPrice());
	pr.setProductImage(product.getProductImage());
	
	Product up = productRepo.save(pr);
	return ResponseEntity.ok(up);
	}
	
	@GetMapping({"/getProductId/{productId}"})
	public Product getProductById(@PathVariable("productId") long productId) {
		return productSer.getProductById(productId);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@DeleteMapping({"/deleteProduct/{productId}"})
	public void deleteProduct(@PathVariable("productId") long productId) {
		productSer.deleteProduct(productId);
	}
	
	@GetMapping({"/getProductDetail/{isSingleProduct}/{productId}"})
	public List<Product> getProductDetail(@PathVariable(name="isSingleProduct")boolean isSingleProduct,
			@PathVariable(name="productId")long productId){
		return productSer.getProductDetail(isSingleProduct, productId);
	}
}
