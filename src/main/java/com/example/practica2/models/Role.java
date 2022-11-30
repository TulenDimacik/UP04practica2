package com.example.practica2.models;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, HUMANOID;


    @Override
    public String getAuthority() {
        return name();
    }
}