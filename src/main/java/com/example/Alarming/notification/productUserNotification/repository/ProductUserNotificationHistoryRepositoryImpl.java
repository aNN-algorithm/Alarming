package com.example.Alarming.notification.productUserNotification.repository;

import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotificationHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductUserNotificationHistoryRepositoryImpl implements ProductUserNotificationHistoryRepository {

    private final ProductUserNotificationHistoryJpaRepository productUserNotificationHistoryJpaRepository;

    @Override
    public ProductUserNotificationHistory save(ProductUserNotificationHistory productUserNotificationHistory) {
        //return productJpaRepository.save(ProductEntity.from(product)).toModel();
        return productUserNotificationHistoryJpaRepository.save(ProductUserNotificationHistoryEntity.from(productUserNotificationHistory)).toModel();
    }

    @Override
    public ProductUserNotificationHistory getLastProductByProductId(Long productId) {
        return productUserNotificationHistoryJpaRepository.findFirstByProductIdOrderBySentAtDesc(productId).toModel();
    }

}
