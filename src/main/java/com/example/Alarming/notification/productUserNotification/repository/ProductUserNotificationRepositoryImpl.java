package com.example.Alarming.notification.productUserNotification.repository;

import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductUserNotificationRepositoryImpl implements ProductUserNotificationRepository {

    ProductUSerNotificationJpaRepository productUserNotificationJpaRepository;

    @Override
    public List<ProductUserNotification> findAllByProductIdAndIsActiveNotificationTrueOrderByModifiedAtDesc(Long productId) {
        return productUserNotificationJpaRepository.findAllByProductIdAndIsActiveNotificationTrueOrderByModifiedAtDesc(productId)
                .stream()
                .map(ProductUserNotificationEntity::toModel)
                .collect(Collectors.toList());
    }
}
