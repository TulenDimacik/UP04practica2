package com.example.practica2.repo;

import com.example.practica2.models.Passport;
import org.springframework.data.repository.CrudRepository;

public interface PassporRepository extends CrudRepository<Passport,Long> {

    Passport findByNumber(String number);
}
