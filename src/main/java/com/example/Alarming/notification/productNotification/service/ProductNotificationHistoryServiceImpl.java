package com.example.Alarming.notification.productNotification.service;

import com.example.Alarming.notification.productNotification.domain.ProductNotificationHistory;
import com.example.Alarming.notification.productNotification.domain.ProductNotificationStatus;
import com.example.Alarming.notification.productNotification.repository.ProductNotificationHistoryRepository;
import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotification;
import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotificationHistory;
import com.example.Alarming.notification.productUserNotification.repository.ProductUserNotificationHistoryRepository;
import com.example.Alarming.notification.productUserNotification.service.ProductUserNotificationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductNotificationHistoryServiceImpl implements ProductNotificationHistoryService {

    private final ProductNotificationHistoryRepository productNotificationHistoryRepository;
    private final ProductUserNotificationHistoryService productUserNotificationHistoryService;

    @Override
    public ProductNotificationHistory getById(Long productId) {
        return productNotificationHistoryRepository.getById(productId);
    }

    @Override
    public ProductNotificationHistory create(Long productId, ProductNotificationStatus status) {
        ProductUserNotificationHistory productUserNotificationHistory = productUserNotificationHistoryService.getLastProductByProductId(productId);
        ProductNotificationHistory productNotificationHistory = ProductNotificationHistory.builder()
                .productId(productId)
                .productNotificationStatus(status)
                .restockRound(productUserNotificationHistory.getRestockRound())
                .lastNotifyUserId(productUserNotificationHistory.getUserId())
                .build();
        return productNotificationHistoryRepository.save(productNotificationHistory);
    }
}
