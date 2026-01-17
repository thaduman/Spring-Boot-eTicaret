package com.oldworld.demo.repository;

import com.oldworld.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByCustomerEmailOrderByOrderDateDesc(String customerEmail);
    
    List<Order> findAllByOrderByOrderDateDesc();

}
