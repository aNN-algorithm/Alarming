package com.example.Alarming.product.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {

    private final Long productId;
    private final Integer restockRound;
    private final Integer stockQuantity;

    @Builder
    public Product(Long productId, Integer restockRound, Integer stockQuantity) {
        this.productId = productId;
        this.restockRound = restockRound;
        this.stockQuantity = stockQuantity;
    }

    public Product update(ProductRequest request) {
        return Product.builder()
                .productId(productId)
                .restockRound(restockRound + 1)
                .stockQuantity(stockQuantity + request.getStockQuantity())
                .build();
    }
}
