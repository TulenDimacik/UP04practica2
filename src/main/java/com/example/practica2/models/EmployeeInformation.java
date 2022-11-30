package com.example.practica2.models;

import javax.persistence.*;

import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table (name = "employee")
public class EmployeeInformation {
    public EmployeeInformation(String employeeName, double salary, int age, float height, boolean deletedEmployee, Passport passport) {
        this.employeeName = employeeName;
        this.salary = salary;
        this.age = age;
        this.height = height;
        this.deletedEmployee = deletedEmployee;
        this.passport = passport;
    }

    public EmployeeInformation() {}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2,max =30,message = "Размер должен быть от 2 до 30")
    private String employeeName;
    @NotNull
    @Min(value = 0,message ="Значение не должно быть меньше 0" )
    private double salary;
    @NotNull
    @Min(value = 0,message ="Значение не должно быть меньше 0" )
    @Max(value = 100,message = "Значение не может быть больще 100")
    private int age;
    @NotNull
    @Min(value = 0,message ="Значение не должно быть меньше 0" )
    private float height;
    private boolean deletedEmployee;

    @OneToOne(optional = true, cascade = CascadeType.ALL)// обязательное или нет каскад - взаимодействие с данными в одной из сторон
    //указываем у главной стороны при удалении пользователя удаляется и сам паспорт
    //name - id паспорта при связи
    @JoinColumn(name="passport_id")
    private Passport passport;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

//    public List<ClientInformation> getClients() {
//        return clients;
//    }
//
//    public void setClients(List<ClientInformation> clients) {
//        this.clients = clients;
//    }
}
