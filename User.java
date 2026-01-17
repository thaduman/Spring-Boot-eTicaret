package com.oldworld.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    
    // TASARIM UYUMU: Frontend 'displayName' beklediği için bu şekilde güncelledik
    private String displayName; 
    
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Jackson ve Hibernate için zorunlu boş kurucu
    public User() {
    }

    // ---- GETTERS ----
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getDisplayName() { return displayName; }
    public String getPhone() { return phone; }
    public Role getRole() { return role; }

    // ---- SETTERS ----
    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setDisplayName(String displayName) { this.displayName = displayName; } 
    public void setPhone(String phone) { this.phone = phone; }             
    public void setRole(Role role) { this.role = role; }
}