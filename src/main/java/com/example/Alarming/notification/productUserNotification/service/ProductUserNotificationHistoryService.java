package com.example.Alarming.notification.productUserNotification.service;

import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotificationHistory;

public interface ProductUserNotificationHistoryService {

    ProductUserNotificationHistory create(Long productId, Long userId);

    ProductUserNotificationHistory getLastProductByProductId(Long productId);
}
