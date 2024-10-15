package com.example.Alarming.common.config;

import com.example.Alarming.notification.productNotification.controller.NotificationManager;
import com.example.Alarming.notification.productNotification.domain.ProductNotificationStatus;
import com.example.Alarming.notification.productNotification.repository.ProductNotificationHistoryRepository;
import com.example.Alarming.notification.productNotification.service.NotificationSender;
import com.example.Alarming.notification.productNotification.service.NotificationWaiting;
import com.example.Alarming.notification.productNotification.service.ProductNotificationHistoryService;
import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotification;
import com.example.Alarming.notification.productUserNotification.repository.ProductUserNotificationRepository;
import com.example.Alarming.notification.productUserNotification.service.ProductUserNotificationHistoryService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.concurrent.*;

@Configuration
@RequiredArgsConstructor
public class AlarmingConfig {

    private static final int TOKEN_REFILL_SECONDS = 1;
    private static final int TOKEN_REFILL_COUNT = 500;
    private final ProductNotificationHistoryRepository productNotificationHistoryRepository;
    private final ProductUserNotificationRepository productUserNotificationRepository;
    private final ProductUserNotificationHistoryService productUserNotificationHistoryService;
    private final ProductNotificationHistoryService productNotificationHistoryService;

    @Bean
    public Bucket notificationBucket() {
        final Refill refill = Refill.intervally(TOKEN_REFILL_COUNT, Duration.ofSeconds(TOKEN_REFILL_SECONDS));
        final Bandwidth limit = Bandwidth.classic(TOKEN_REFILL_COUNT, refill);
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @Bean
    public ConcurrentHashMap<Long, ProductNotificationStatus> productStatus() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public BlockingDeque<ProductUserNotification> notificationQueue() {
        return new LinkedBlockingDeque<>();
    }

    @Bean
    public NotificationManager notificationManager() {
        return new NotificationManager(productStatus(), notificationWaiting(), notificationSender());
    }

    @Bean
    public NotificationSender notificationSender() {
        return new NotificationSender(notificationBucket(), productStatus(), notificationQueue(), productUserNotificationHistoryService, productNotificationHistoryService);
    }

    @Bean
    public NotificationWaiting notificationWaiting() {
        return new NotificationWaiting(notificationQueue(), productUserNotificationRepository);
    }
}
