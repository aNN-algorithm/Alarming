package com.example.Alarming.product.infrastructure;

import com.example.Alarming.common.exception.ResourceNotFoundException;
import com.example.Alarming.product.domain.Product;
import com.example.Alarming.product.service.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product getById(Long id) {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    @Override
    public Optional<Product> findById(Long id) {
        // DB에서 가져온 Entity를 도메인으로 변경(map)하도록 ProductEntity::toModel 을 참조
        return productJpaRepository.findById(id).map(ProductEntity::toModel);
    }

    @Override
    public Product save(Product product) {
        // Product 객체를 입력으로 받아 그 데이터를 바탕으로 ProductEntity 형태의 새로운 객체를 생성하도록 static 메서드로 정의
        return productJpaRepository.save(ProductEntity.from(product)).toModel();
    }


}
