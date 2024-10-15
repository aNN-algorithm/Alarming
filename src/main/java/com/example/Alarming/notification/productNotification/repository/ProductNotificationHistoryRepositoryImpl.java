package com.example.Alarming.notification.productNotification.repository;

import com.example.Alarming.common.exception.ResourceNotFoundException;
import com.example.Alarming.notification.productNotification.domain.ProductNotificationHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductNotificationHistoryRepositoryImpl implements ProductNotificationHistoryRepository {

    private final ProductNotificationHistoryJpaRepository productNotificationHistoryJpaRepository;

    @Override
    public ProductNotificationHistory getById(Long productId) {
        return findById(productId).orElseThrow(() -> new ResourceNotFoundException("ProductNotificationHistory", productId));
    }

    @Override
    public Optional<ProductNotificationHistory> findById(Long productId) {
        return productNotificationHistoryJpaRepository.findById(productId).map(ProductNotificationHistoryEntity::toModel);
    }

    @Override
    public List<ProductNotificationHistory> findAllById(Long productId) {
        return productNotificationHistoryJpaRepository.findAllById(Collections.singleton(productId))
                .stream() // 스트림으로 변환
                .map(ProductNotificationHistoryEntity::toModel) // 엔티티를 모델로 변환
                .collect(Collectors.toList());
    }

    @Override
    public ProductNotificationHistory save(ProductNotificationHistory productNotificationHistory) {
        return productNotificationHistoryJpaRepository.save(ProductNotificationHistoryEntity.from(productNotificationHistory)).toModel();
    }
}
