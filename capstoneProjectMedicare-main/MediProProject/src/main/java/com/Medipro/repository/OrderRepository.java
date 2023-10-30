package com.Medipro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Medipro.enitity.*;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails, Long> {

}
