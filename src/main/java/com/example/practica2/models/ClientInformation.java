package com.example.practica2.models;

import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class ClientInformation {
    public ClientInformation(String clientName, double orderCost, int clientAge, float orderWeight, boolean payment, Adress address) {
        this.clientName = clientName;
        this.orderCost = orderCost;
        this.clientAge = clientAge;
        this.orderWeight = orderWeight;
        this.payment = payment;
        this.address = address;
    }
    @ManyToMany
    @JoinTable (name="client_product",
            joinColumns=@JoinColumn (name="client_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    private List<Product> products;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Adress address;
    public ClientInformation() {}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2,max =30,message = "Размер должен быть от 2 до 30")
    private String clientName;
    @NotNull
    @Min(value = 0,message ="Значение не должно быть меньше 0" )
    private double orderCost;
    @NotNull
    @Min(value = 0,message ="Значение не должно быть меньше 0" )
    @Max(value = 100,message = "Значение не может быть больще 100")
    private int clientAge;

    @NotNull
    @Min(value = 0,message ="Значение не должно быть меньше 0" )
    private float orderWeight;
    private boolean payment;

    public String getClientName() {return clientName;}

    public void setClientName(String clientName) {this.clientName = clientName;}

    public double getOrderCost() {return orderCost;}

    public void setOrderCost(double orderCost) {this.orderCost = orderCost;}

    public int getClientAge() {return clientAge;}

    public void setClientAge(int clientAge) {this.clientAge = clientAge;}

    public float getOrderWeight() {return orderWeight;}

    public void setOrderWeight(float orderWeight) {this.orderWeight = orderWeight;}

    public boolean isPayment() {return payment;}

    public void setPayment(boolean payment) {this.payment = payment;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Adress getAddress() {
        return address;
    }

    public void setAddress(Adress address) {
        this.address = address;
    }
}
