package com.example.Alarming.product.service;

import com.example.Alarming.product.domain.Product;
import com.example.Alarming.product.domain.ProductRequest;
import com.example.Alarming.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final int INITIAL_RESTOCK_ROUND = 0;

    public void create(ProductRequest request) {
        Product product = new Product(request.getProductId(),
                INITIAL_RESTOCK_ROUND, request.getStockQuantity());

        productRepository.save(product);
    }
}
