package com.greenmarket.backend.dto;

public class RegisterRequest {
    private String nama_lengkap;
    private String email;
    private String password;

    public String getNama_lengkap() { return nama_lengkap; }
    public void setNama_lengkap(String nama_lengkap) { this.nama_lengkap = nama_lengkap; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}