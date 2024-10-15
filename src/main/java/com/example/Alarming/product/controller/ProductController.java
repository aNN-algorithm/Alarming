package com.example.Alarming.product.controller;

import com.example.Alarming.notification.productNotification.controller.NotificationManager;
import com.example.Alarming.product.domain.Product;
import com.example.Alarming.product.domain.ProductRequest;
import com.example.Alarming.product.service.port.ProductRestockService;
import com.example.Alarming.product.service.port.ProductService;
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
    private final NotificationManager notificationManager;

    @PostMapping("/product")
    public ResponseEntity<Void> create(@RequestBody ProductRequest request) {
        productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 재입고
    @PostMapping("/product/{productId}/notification/re-stock")
    public ResponseEntity<Void> create(@PathVariable Long productId,
                                                         @RequestBody ProductRequest request) {
        Product product = productRestockService.create(productId, request); // Product 객체 업데이트
        notificationManager.update(product); // 해당 재고 업데이트 알림
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
