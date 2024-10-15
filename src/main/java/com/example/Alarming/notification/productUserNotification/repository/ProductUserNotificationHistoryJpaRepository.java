package com.example.Alarming.notification.productUserNotification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductUserNotificationHistoryJpaRepository extends JpaRepository<ProductUserNotificationHistoryEntity, Long> {

    ProductUserNotificationHistoryEntity findFirstByProductIdOrderBySentAtDesc(Long productId);
}
