package com.example.Alarming.notification.productNotification.service;

import com.example.Alarming.notification.productNotification.domain.ProductNotificationHistory;
import com.example.Alarming.notification.productNotification.domain.ProductNotificationStatus;
import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotification;
import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotificationHistory;
import com.example.Alarming.notification.productUserNotification.service.ProductUserNotificationHistoryService;
import com.example.Alarming.product.domain.Product;
import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class NotificationSender implements Runnable {

    private final Bucket bucket;
    private final ConcurrentHashMap<Long, ProductNotificationStatus> productStatus;
    private final BlockingDeque<ProductUserNotification> notificationQueue;
    private final ProductUserNotificationHistoryService productUserNotificationHistoryService;
    private final ProductNotificationHistoryService productNotificationHistoryService;

    @Override
    public void run() {

        while (true) {
            if (notificationQueue.isEmpty()) continue;

            Long productId = notificationQueue.peek().getProductId();
            ProductNotificationStatus productNotificationStatus = productStatus.get(productId);
            ProductUserNotification cur = null;
            try {
                cur = notificationQueue.take();

                if (bucket.tryConsume(1) && isInProgress(productNotificationStatus)) {

                    // 마지막 알림을 표시
                    if (cur.isEnd()) {
                        productNotificationHistoryService.create(productId, ProductNotificationStatus.COMPLETED);
                    }

                    // 유저 알림 히스토리
                    productUserNotificationHistoryService.create(cur.getProductId(), cur.getUserId());
                } else if (isSoldOut(productNotificationStatus)) {
                    // 마지막을 표시하려면
                    // ProductUserNotificationHistory 테이블에서 해당 productId를 가진 마지막 조회해서 저장하기
                    productNotificationHistoryService.create(productId, ProductNotificationStatus.CANCELED_BY_SOLD_OUT);
                    productStatus.put(productId, ProductNotificationStatus.PASS);
                } else if (isInProgress(productNotificationStatus)) {
                    notificationQueue.addFirst(cur);
                }

                // 마지막 알림까지 전송되면 맵에서 제거
                if (cur.isEnd()) {
                    productStatus.remove(productId);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isInProgress(ProductNotificationStatus productNotificationStatus) {
        return productNotificationStatus.equals(ProductNotificationStatus.IN_PROGRESS);
    }

    private boolean isSoldOut(ProductNotificationStatus productNotificationStatus) {
        return productNotificationStatus == ProductNotificationStatus.SOLD_OUT;
    }

}
