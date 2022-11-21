package com.example.practica2.models;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class EmployeeInformation {
    public EmployeeInformation(String employeeName, double salary, int age, float height, boolean deletedEmployee) {
        this.employeeName = employeeName;
        this.salary = salary;
        this.age = age;
        this.height = height;
        this.deletedEmployee = deletedEmployee;
    }

    public EmployeeInformation() {}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String employeeName;
    private double salary;
    private int age;
    private float height;
    private boolean deletedEmployee;
    public String getEmployeeName() {return employeeName;}

    public void setEmployeeName(String employeeName) {this.employeeName = employeeName;}
    public double getSalary() {return salary;}

    public void setSalary(double salary) {this.salary = salary;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public float getHeight() {return height;}

    public void setHeight(float height) {this.height = height;}

    public boolean isDeletedEmployee() {return deletedEmployee;}

    public void setDeletedEmployee(boolean deletedEmployee) {this.deletedEmployee = deletedEmployee;}
}
