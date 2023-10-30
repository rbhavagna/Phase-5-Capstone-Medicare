package com.Medipro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Medipro.enitity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "select p from Product p where p.productCategory=:productCategory")
	List<Product> findAllByCategory(@Param("productCategory") String category);
	
	List<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
			   String key1,String key2
			);
}
