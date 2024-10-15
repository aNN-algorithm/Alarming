package com.example.Alarming.notification.productNotification.repository;

import com.example.Alarming.notification.productNotification.domain.ProductNotificationHistory;

import java.util.List;
import java.util.Optional;

public interface ProductNotificationHistoryRepository {

    ProductNotificationHistory getById(Long productId);

    Optional<ProductNotificationHistory> findById(Long productId);

    List<ProductNotificationHistory> findAllById(Long productId);

    ProductNotificationHistory save(ProductNotificationHistory productNotificationHistory);
}
