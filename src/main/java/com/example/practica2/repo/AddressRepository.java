package com.example.practica2.repo;

import com.example.practica2.models.Adress;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Adress, Long> {
    Adress findByStreet(String street);
}
