package com.oldworld.demo.dto;

import lombok.Data; 
import lombok.NoArgsConstructor;

public class AuthRequest {
    private String email;
    private String password;
    
    // ----------- KRİTİK ÇÖZÜM: BOŞ KURUCU EKLENDİ (image_8fb167.png'deki gibi) -----------
    public AuthRequest() {
    }
    // ---------------------------------------------------------------------------------

    // Getter'lar
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    
    // Setter'lar (Her iki alan için de doğru atamalar eklendi)
    public void setEmail(String email) { this.email = email; } 
    public void setPassword(String password) { this.password = password; } // KRİTİK DÜZELTME
}