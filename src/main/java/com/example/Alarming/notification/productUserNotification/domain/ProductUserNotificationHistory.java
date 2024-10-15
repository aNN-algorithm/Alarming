package com.example.Alarming.notification.productUserNotification.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductUserNotificationHistory {

    private Long productId;
    private Long userId;
    private Integer restockRound;
    private LocalDateTime sentAt;

    @Builder
    public ProductUserNotificationHistory(Long productId,
                                          Long userId,
                                          Integer restockRound,
                                          LocalDateTime sentAt
    ) {
        this.productId = productId;
        this.userId = userId;
        this.restockRound = restockRound;
        this.sentAt = sentAt;
    }
}
