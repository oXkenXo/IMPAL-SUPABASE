package com.greenmarket.backend.service;

import com.greenmarket.backend.dto.LoginRequest;
import com.greenmarket.backend.dto.RegisterRequest;
import com.greenmarket.backend.model.User;
import com.greenmarket.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public ResponseEntity<Map<String, String>> register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Email sudah terdaftar"));
        }

        User user = new User();
        // Ambil 'nama_lengkap' dari frontend/DTO dan set ke kolom 'nama'
        user.setNama(request.getNama_lengkap()); 
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("user"); // Pastikan 'user' huruf kecil sesuai SQL

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Registrasi berhasil"));
    }

    public ResponseEntity<Map<String, Object>> login(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        
        if (optionalUser.isEmpty() || !passwordEncoder.matches(request.getPassword(), optionalUser.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Email atau password salah"));
        }

        User user = optionalUser.get();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login berhasil");
        response.put("user", Map.of(
                "id", user.getId_user(), // Gunakan id_user
                "nama", user.getNama(),
                "email", user.getEmail(),
                "role", user.getRole()
        ));

        return ResponseEntity.ok(response);
    }
}