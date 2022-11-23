package com.example.practica2.models;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class ClientInformation {
    public ClientInformation(String clientName, double orderCost, int clientAge, float orderWeight, boolean payment) {
        this.clientName = clientName;
        this.orderCost = orderCost;
        this.clientAge = clientAge;
        this.orderWeight = orderWeight;
        this.payment = payment;
    }
    public ClientInformation() {}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clientName;
    private double orderCost;
    private int clientAge;
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
}
