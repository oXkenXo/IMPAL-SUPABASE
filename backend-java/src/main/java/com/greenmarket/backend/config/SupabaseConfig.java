package com.greenmarket.backend.config;

public class SupabaseConfig {
    private String supabaseUrl;
    private String supabaseKey;

    public String getSupabaseUrl() {
        return supabaseUrl;
    }

    public void setSupabaseUrl(String supabaseUrl) {
        this.supabaseUrl = supabaseUrl;
    }

    public String getSupabaseKey() {
        return supabaseKey;
    }

    public void setSupabaseKey(String supabaseKey) {
        this.supabaseKey = supabaseKey;
    }
}
