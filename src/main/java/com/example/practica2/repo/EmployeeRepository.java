package com.example.practica2.repo;

import com.example.practica2.models.ClientInformation;
import com.example.practica2.models.EmployeeInformation;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<EmployeeInformation, Long> {
    List<EmployeeInformation> findByEmployeeNameContains (String employeeName);
    List<EmployeeInformation> findByEmployeeNameEquals  (String employeeName);
}
