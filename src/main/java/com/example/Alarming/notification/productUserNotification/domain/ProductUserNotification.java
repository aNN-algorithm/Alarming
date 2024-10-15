package com.example.Alarming.notification.productUserNotification.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ProductUserNotification {

    private Long id;
    private Long productId;
    private Long userId;
    private boolean isActiveNotification;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean isEnd = false;

    @Builder
    public ProductUserNotification(Long id,
                                   Long productId,
                                   Long userId,
                                   boolean isActiveNotification,
                                   LocalDateTime createdAt,
                                   LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.isActiveNotification = isActiveNotification;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void endNotification(boolean isEnd) {
        this.isEnd = isEnd;
    }
}
