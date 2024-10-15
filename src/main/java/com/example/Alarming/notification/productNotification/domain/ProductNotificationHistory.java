package com.example.Alarming.notification.productNotification.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductNotificationHistory {

    private final Long productId;
    private final Integer restockRound;
    private final ProductNotificationStatus productNotificationStatus;
    private final Long lastNotifyUserId;

    @Builder
    public ProductNotificationHistory(Long productId,
                                      Integer restockRound,
                                      ProductNotificationStatus productNotificationStatus,
                                      Long lastNotifyUserId
    ) {
        this.productId = productId;
        this.restockRound = restockRound;
        this.productNotificationStatus = productNotificationStatus;
        this.lastNotifyUserId = lastNotifyUserId;
    }
}
