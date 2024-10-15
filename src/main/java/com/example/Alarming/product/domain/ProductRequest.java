package com.example.Alarming.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductRequest {

    private Long productId;
    private Integer stockQuantity;
}
