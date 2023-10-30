package com.Medipro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Medipro.enitity.Cart;
import com.Medipro.enitity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	public List<Cart> findByUser(User user);
}
