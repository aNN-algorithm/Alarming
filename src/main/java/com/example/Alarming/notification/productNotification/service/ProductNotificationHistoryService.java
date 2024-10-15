package com.example.Alarming.notification.productNotification.service;

import com.example.Alarming.notification.productNotification.domain.ProductNotificationHistory;
import com.example.Alarming.notification.productNotification.domain.ProductNotificationStatus;

public interface ProductNotificationHistoryService {

    ProductNotificationHistory getById(Long productId);

    ProductNotificationHistory create(Long productId, ProductNotificationStatus status);
}
