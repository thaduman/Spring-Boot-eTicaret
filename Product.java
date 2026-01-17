package com.oldworld.demo.model; 

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 1000)
    private String description;
    private BigDecimal price; // BigDecimal kullanımı en profesyonelidir
    private String category;
    private String imageUrl;
    private Integer stockQuantity;
    private String sellerEmail; 
}