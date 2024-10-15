package com.example.Alarming.notification.productNotification.service;

import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotification;
import com.example.Alarming.notification.productUserNotification.repository.ProductUserNotificationRepository;
import com.example.Alarming.product.domain.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.BlockingDeque;

@RequiredArgsConstructor
public class NotificationWaiting {

    private final BlockingDeque<ProductUserNotification> notificationQueue;
    private final ProductUserNotificationRepository productUserNotificationRepository;

    public void create(Product product) {
        // 그럼 이제 Queue 에 알림을 전송할 객체들이 저장됨
        List<ProductUserNotification> productUserNotifications
                = productUserNotificationRepository.findAllByProductIdAndIsActiveNotificationTrueOrderByModifiedAtDesc(product.getProductId());
        ProductUserNotification productUserNotification = productUserNotifications.get(productUserNotifications.size() - 1);
        productUserNotification.endNotification(true);
        notificationQueue.addAll(productUserNotifications);

    }
}
