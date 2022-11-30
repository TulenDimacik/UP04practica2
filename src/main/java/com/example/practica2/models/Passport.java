package com.example.practica2.models;


import javax.persistence.*;

@Entity
@Table(name = "passports")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String series;
    private String number;
    @OneToOne(optional = true, mappedBy = "passport")//та же аннотация mappedBY сторона которая ссылается на переменную в Person
    private EmployeeInformation owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public EmployeeInformation getOwner() {
        return owner;
    }

    public void setOwner(EmployeeInformation owner) {
        this.owner = owner;
    }
}