package com.example.Alarming.product.service;

import com.example.Alarming.product.domain.Product;
import com.example.Alarming.product.domain.ProductRequest;
import com.example.Alarming.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductRestockServiceImpl implements ProductRestockService {

    private final ProductRepository productRepository;

    @Override
    public Product create(Long productId, ProductRequest request) {
        // 해당 상품이 있는지 확인
        // 리포지토리에서 id를 검색한 후 도메인에 저장
        // 그 도메인 내 인증을 하고 다시 인증한 결과를 저장
        Product product = productRepository.getById(productId);

        // 재입고 회차 1 증가
        product = product.update(request);
        productRepository.save(product);
        return product;
    }

    // sell
    // 기업과제 측면에서 구현할 것
}
