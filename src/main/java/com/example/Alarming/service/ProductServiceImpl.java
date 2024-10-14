package com.example.Alarming.service;

import com.example.Alarming.domain.Product;
import com.example.Alarming.domain.ProductRequest;
import com.example.Alarming.service.port.ProductRepository;
import com.example.Alarming.service.port.ProductService;
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
