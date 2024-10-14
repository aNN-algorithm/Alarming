package com.example.Alarming.controller;

import com.example.Alarming.domain.ProductRequest;
import com.example.Alarming.service.port.ProductRestockService;
import com.example.Alarming.service.port.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductRestockService productRestockService;

    @PostMapping("/product")
    public ResponseEntity<Void> create(@RequestBody ProductRequest request) {
        productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/product/{productId}/notification/re-stock")
    public ResponseEntity<Void> create(@PathVariable Long productId,
                                                         @RequestBody ProductRequest request) {
        productRestockService.create(productId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
