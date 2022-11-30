package com.example.practica2.models;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, SOTRUDNIK;


    @Override
    public String getAuthority() {
        return name();
    }
}