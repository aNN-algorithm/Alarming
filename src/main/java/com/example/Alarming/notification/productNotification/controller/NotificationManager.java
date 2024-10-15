package com.example.Alarming.notification.productNotification.controller;

import com.example.Alarming.notification.productNotification.domain.ProductNotificationStatus;
import com.example.Alarming.notification.productNotification.service.NotificationSender;
import com.example.Alarming.notification.productNotification.service.NotificationWaiting;
import com.example.Alarming.product.domain.Product;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class NotificationManager {

    private final ConcurrentHashMap<Long, ProductNotificationStatus> productStatus;
    private final NotificationWaiting notificationWaiting;
    private final NotificationSender notificationSender;

    @PostConstruct
    public void init() {
        // Sender 생성
        new Thread(notificationSender).start();
    }

    public void update(Product product) {

        // 해당 상품이 이미 map 에 있는 경우 == 이미 재입고 알림을 보내기 위해 대기하는 중
        // 해당 상품이 map 에 없으면
        if (!productStatus.containsKey(product.getProductId())) {
            productStatus.put(product.getProductId(), ProductNotificationStatus.IN_PROGRESS); // 해당 상품은 map 에 존재
            notificationWaiting.create(product); // 해당 상품을 구독한 사람들을 알림 대기 큐에 등록
        }
    }

    public void soldout(Product product) {
        productStatus.put(product.getProductId(), ProductNotificationStatus.SOLD_OUT);
    }
}
