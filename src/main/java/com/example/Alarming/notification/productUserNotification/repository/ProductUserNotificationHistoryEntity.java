package com.example.Alarming.notification.productUserNotification.repository;

import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotification;
import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotificationHistory;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_user_notification_history")
public class ProductUserNotificationHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "restock_round", nullable = false)
    private Integer restockRound;

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    public ProductUserNotificationHistory toModel() {
        return ProductUserNotificationHistory.builder()
                .productId(productId)
                .userId(userId)
                .restockRound(restockRound)
                .sentAt(sentAt)
                .build();
    }

    public static ProductUserNotificationHistoryEntity from(ProductUserNotificationHistory productUserNotificationHistory) {
        ProductUserNotificationHistoryEntity productUserNotificationHistoryEntity = new ProductUserNotificationHistoryEntity();
        productUserNotificationHistoryEntity.productId = productUserNotificationHistory.getProductId();
        productUserNotificationHistoryEntity.userId = productUserNotificationHistory.getUserId();
        productUserNotificationHistoryEntity.restockRound = productUserNotificationHistory.getRestockRound();
        productUserNotificationHistoryEntity.sentAt = productUserNotificationHistory.getSentAt();
        return productUserNotificationHistoryEntity;
    }
}
