package com.oldworld.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerEmail; 
    private Double totalAmount; 
    private String address;
    private String status; 
    private LocalDateTime orderDate;

    // mappedBy ekleyerek çift yönlü ilişkiyi kurduk
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {
        this.orderDate = LocalDateTime.now();
        this.status = "COMPLETED"; 
    }

    // ZIRHLI METOT: İlişkinin her iki tarafını da aynı anda set eder
    public void addOrderItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }
}