package com.example.Alarming.product.service.port;

import com.example.Alarming.product.domain.ProductRequest;

public interface ProductRestockService {

    void create(Long productId, ProductRequest request);
}
