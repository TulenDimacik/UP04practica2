package com.example.practica2.repo;

import com.example.practica2.models.ClientInformation;
import com.example.practica2.models.EmployeeInformation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<ClientInformation, Long> {
    List<ClientInformation> findByClientNameContains (String clientName);
    List<ClientInformation> findByClientNameEquals  (String clientName);
}
