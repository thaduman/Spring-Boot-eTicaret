package com.oldworld.demo.dto;

import lombok.Data; 
import lombok.NoArgsConstructor;

public class AuthRequest {
    private String email;
    private String password;
    
    public AuthRequest() {
    }

    // Getter'lar
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    
    // Setter'lar 
    public void setEmail(String email) { this.email = email; } 
    public void setPassword(String password) { this.password = password; } // KRİTİK DÜZELTME

}
