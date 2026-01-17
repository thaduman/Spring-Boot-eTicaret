package com.oldworld.demo.repository;

import com.oldworld.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    // Kullanıcının geçmiş siparişlerini "MyOrders" sayfasında asaletle sergilemek için
    List<Order> findByCustomerEmailOrderByOrderDateDesc(String customerEmail);
    
    // Satıcı paneli için tüm siparişleri kronolojik listeleme
    List<Order> findAllByOrderByOrderDateDesc();
}