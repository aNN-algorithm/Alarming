package com.example.Alarming.notification.productNotification.repository;

import com.example.Alarming.notification.productNotification.domain.ProductNotificationHistory;
import com.example.Alarming.notification.productNotification.domain.ProductNotificationStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "product_notification_history")
public class ProductNotificationHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_round", nullable = false)
    private Integer restockRound;

    @Column(name = "product_notification_status", nullable = false)
    private ProductNotificationStatus productNotificationStatus;

    @Column(name = "last_notify_user_id", nullable = false)
    private Long lastNotifyUserId;

    public ProductNotificationHistory toModel() {
        return ProductNotificationHistory.builder()
                .productId(id)
                .restockRound(restockRound)
                .productNotificationStatus(productNotificationStatus)
                .lastNotifyUserId(lastNotifyUserId)
                .build();
    }

    public static ProductNotificationHistoryEntity from(ProductNotificationHistory productNotificationHistory) {
        ProductNotificationHistoryEntity productNotificationHistoryEntity = new ProductNotificationHistoryEntity();
        productNotificationHistoryEntity.id = productNotificationHistory.getProductId();
        productNotificationHistoryEntity.restockRound = productNotificationHistory.getRestockRound();
        productNotificationHistoryEntity.productNotificationStatus = productNotificationHistory.getProductNotificationStatus();
        productNotificationHistoryEntity.lastNotifyUserId = productNotificationHistory.getLastNotifyUserId();
        return productNotificationHistoryEntity;
    }
}
