package com.oldworld.demo.controller;

import com.oldworld.demo.dto.AuthRequest;
import com.oldworld.demo.model.Role; // Role enum veya modelin
import com.oldworld.demo.model.User;
import com.oldworld.demo.repository.UserRepository;
import com.oldworld.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Şifreleme için eklendi
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repo;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder; // Şifreleme için eklendi

    // --- KAYIT OL METODU (EKSİK OLAN KISIM) ---
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        System.out.println("YENİ KAYIT DENEMESİ: " + user.getEmail());
        
        try {
            // 1. Email kontrolü
            if (repo.existsByEmail(user.getEmail())) {
                return "Hata: Email zaten kayıtlı";
            }

            // 2. Şifreyi BCrypt ile şifrele (Login olabilmek için ŞART)
            user.setPassword(encoder.encode(user.getPassword()));
            
            // 3. Varsayılan rol ata
            user.setRole(Role.USER);

            // 4. Veritabanına kaydet
            repo.save(user);
            
            System.out.println("KAYIT BAŞARILI: " + user.getEmail());
            return "Kayıt başarılı";
        } catch (Exception e) {
            System.out.println("KAYIT HATASI: " + e.getMessage());
            return "Hata: " + e.getMessage();
        }
    }

    // --- GİRİŞ YAP METODU ---
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest req) {
        System.out.println("LOGIN DENEMESİ: " + req.getEmail());
        
        try {
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
            );
            System.out.println("GİRİŞ BAŞARILI, TOKEN ÜRETİLİYOR...");
            return jwtUtil.generateToken(req.getEmail());
        } catch (Exception e) {
            System.out.println("GİRİŞ HATASI: " + e.getMessage());
            // React tarafında 403 veya 401 olarak yakalanması için hata fırlatabiliriz
            // Şimdilik string dönüyoruz
            return "Hata: Email veya şifre yanlış";
        }
    }
}