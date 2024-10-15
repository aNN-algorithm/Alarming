package com.example.Alarming.notification.productUserNotification.repository;

import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotification;

import java.util.List;

public interface ProductUserNotificationRepository {

    List<ProductUserNotification> findAllByProductIdAndIsActiveNotificationTrueOrderByModifiedAtDesc(Long productId);
}
