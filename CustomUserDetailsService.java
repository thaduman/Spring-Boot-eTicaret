package com.oldworld.demo.service;

import com.oldworld.demo.model.User;
import com.oldworld.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor; // Lombok kullanıyorsanız
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor 
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo; // final olarak tanımlandı


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + email));

        // Spring Security'nin kendi User nesnesini döndürüyoruz
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                // Buradaki user.getPassword() metodu HASHED şifreyi döndürmelidir.
                .password(user.getPassword()) 
                .roles(user.getRole().name())
                .build();
    }

}
