package com.example.Alarming.product.service.port;

import com.example.Alarming.product.domain.Product;
import com.example.Alarming.product.domain.ProductRequest;

public interface ProductRestockService {

    Product create(Long productId, ProductRequest request);
}
