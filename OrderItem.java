package com.oldworld.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;   
    private String productName;
    private Integer quantity; 
    private Double price;     
    private String size;      

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @com.fasterxml.jackson.annotation.JsonIgnore // JSON döngüsünü engeller
    private Order order;

    public OrderItem() {}
}