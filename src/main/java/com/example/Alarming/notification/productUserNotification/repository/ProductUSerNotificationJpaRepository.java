package com.example.Alarming.notification.productUserNotification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductUSerNotificationJpaRepository extends JpaRepository<ProductUserNotificationEntity, Long> {
    List<ProductUserNotificationEntity> findAllByProductIdAndIsActiveNotificationTrueOrderByModifiedAtDesc(Long productId);
}
