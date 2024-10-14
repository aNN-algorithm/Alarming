package com.example.Alarming.product.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {

    private final Long id;
    private final Integer restockRound;
    private final Integer stockQuantity;

    @Builder
    public Product(Long id, Integer restockRound, Integer stockQuantity) {
        this.id = id;
        this.restockRound = restockRound;
        this.stockQuantity = stockQuantity;
    }

    public Product update(ProductRequest request) {
        return Product.builder()
                .id(id)
                .restockRound(restockRound + 1)
                .stockQuantity(stockQuantity + request.getStockQuantity())
                .build();
    }
}
