package com.example.practica2.models;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.*;

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
}
