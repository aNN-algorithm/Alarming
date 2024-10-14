package com.example.Alarming.service.port;

import com.example.Alarming.domain.Product;

import java.util.Optional;

public interface ProductRepository {

    Product getById(Long id);

    Optional<Product> findById(Long id);

    Product save(Product product);
}
