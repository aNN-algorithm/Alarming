package com.example.Alarming.notification.productUserNotification.repository;

import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotification;
import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotificationHistory;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_user_notification")
public class ProductUserNotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "is_active_notification", nullable = false)
    private boolean isActiveNotification;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    public ProductUserNotification toModel() {
        return ProductUserNotification.builder()
                .id(id)
                .productId(productId)
                .userId(userId)
                .isActiveNotification(isActiveNotification)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }

//    public static ProductUserNotificationEntity from(ProductUserNotification productUserNotification) {
//        productUserNotificationEntity.id = productUserNotification.getId();
//        productUserNotificationEntity.productId = productUserNotification.getProductId();
//        productUserNotificationEntity.userId = productUserNotification.getUserId();
//        productUserNotificationEntity.isActiveNotification = productUserNotification.isActiveNotification();
//        productUserNotificationEntity.createdAt = productUserNotification.getCreatedAt();
//        productUserNotificationEntity.modifiedAt = productUserNotification.getModifiedAt();
//        return productUserNotificationEntity;
//    }
}
