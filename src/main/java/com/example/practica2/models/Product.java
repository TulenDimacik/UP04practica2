package com.example.practica2.models;

import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Product {

    @ManyToMany
    @JoinTable (name="client_product",
            joinColumns=@JoinColumn (name="product_id"),
            inverseJoinColumns=@JoinColumn(name="client_id"))
    private List<ClientInformation> clients;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<ClientInformation> getClients() {
        return clients;
    }

    public void setClients(List<ClientInformation> clients) {
        this.clients = clients;
    }
}
