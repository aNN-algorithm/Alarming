package com.example.Alarming.product.service.port;

import com.example.Alarming.product.domain.Product;

import java.util.Optional;

public interface ProductRepository {

    Product getById(Long id);

    Optional<Product> findById(Long id);

    Product save(Product product);
}
