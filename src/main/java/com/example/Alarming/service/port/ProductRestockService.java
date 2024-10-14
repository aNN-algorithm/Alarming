package com.example.Alarming.service.port;

import com.example.Alarming.domain.ProductRequest;

public interface ProductRestockService {

    void create(Long productId, ProductRequest request);
}
