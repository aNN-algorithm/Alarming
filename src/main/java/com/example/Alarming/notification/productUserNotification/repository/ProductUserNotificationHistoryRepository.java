package com.example.Alarming.notification.productUserNotification.repository;

import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotificationHistory;

public interface ProductUserNotificationHistoryRepository {

    ProductUserNotificationHistory save(ProductUserNotificationHistory productUserNotificationHistory);

    ProductUserNotificationHistory getLastProductByProductId(Long productId);
}
