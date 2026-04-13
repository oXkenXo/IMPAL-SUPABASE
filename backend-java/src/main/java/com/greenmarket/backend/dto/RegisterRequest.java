package com.greenmarket.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank(message = "Nama lengkap wajib diisi")
    @Size(min = 3, message = "Nama minimal 3 karakter")
    private String nama_lengkap;

    @NotBlank(message = "Email wajib diisi")
    @Email(message = "Email tidak valid")
    private String email;

    @NotBlank(message = "Password wajib diisi")
    @Size(min = 12, message = "Password minimal 12 karakter")
    private String password;

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
