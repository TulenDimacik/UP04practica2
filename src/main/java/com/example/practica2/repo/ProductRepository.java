package com.example.practica2.repo;

import com.example.practica2.models.Passport;
import com.example.practica2.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {

    Product findByProductName(String productName);
}
