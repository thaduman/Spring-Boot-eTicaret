package com.oldworld.demo.repository;

import com.oldworld.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // İhtiyaca göre kategoriye göre filtreleme ekleyebiliriz
    List<Product> findByCategory(String category);
}