package com.oldworld.demo.controller;

import com.oldworld.demo.model.Order;
import com.oldworld.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // CORS kilitlenmesini kesin olarak engeller
public class OrderController {

    private final OrderRepository orderRepository;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        try {
            if (order.getItems() == null || order.getItems().isEmpty()) {
                return ResponseEntity.badRequest().body("Hata: Koleksiyon seçilmeden sipariş verilemez.");
            }

            order.getItems().forEach(item -> item.setOrder(order));

            order.setOrderDate(LocalDateTime.now());
            order.setStatus("COMPLETED"); 
            
            Order savedOrder = orderRepository.save(order);
            return ResponseEntity.ok(savedOrder);
            
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Sipariş işlenirken bir sorun oluştu: " + e.getMessage());
        }
    }
    
    // 2. Kullanıcının Sipariş Geçmişi (MyOrders Entegrasyonu)
    @GetMapping("/my-orders/{email}")
    public ResponseEntity<?> getMyOrders(@PathVariable String email) {
        try {
            List<Order> orders = orderRepository.findByCustomerEmailOrderByOrderDateDesc(email);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Siparişleriniz yüklenirken bir hata oluştu.");
        }
    }

    // 3. Sipariş Durumu Güncelleme (Admin/Satıcı Paneli İçin)
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody String newStatus) {
        return orderRepository.findById(id).map(order -> {
            // JSON'dan gelebilecek ekstra tırnakları temizle
            String cleanStatus = newStatus.replace("\"", "").trim();
            order.setStatus(cleanStatus);
            orderRepository.save(order);
            return ResponseEntity.ok("Sipariş durumu '" + cleanStatus + "' olarak güncellendi.");
        }).orElse(ResponseEntity.notFound().build());
    }

}
