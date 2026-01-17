package com.oldworld.demo.controller;

import com.oldworld.demo.model.User;
import com.oldworld.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") // Frontend erişim izni
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody User userDetails) {
        return userRepository.findById(id).map(user -> {
            // Sadece gelen verileri güncelle, mevcutları koru
            if (userDetails.getDisplayName() != null) user.setDisplayName(userDetails.getDisplayName());
            if (userDetails.getPhone() != null) user.setPhone(userDetails.getPhone());
            
            // Email güncellemesi istenirse (isteğe bağlı)
            if (userDetails.getEmail() != null) user.setEmail(userDetails.getEmail());

            User savedUser = userRepository.save(user);
            
            // GÜVENLİK: Şifreyi asla Frontend'e geri gönderme
            savedUser.setPassword(null);
            
            return ResponseEntity.ok(savedUser);
        }).orElse(ResponseEntity.notFound().build());
    }
}