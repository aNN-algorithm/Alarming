package com.example.Alarming.notification.productUserNotification.service;

import com.example.Alarming.notification.productUserNotification.domain.ProductUserNotificationHistory;
import com.example.Alarming.notification.productUserNotification.repository.ProductUserNotificationHistoryRepository;
import com.example.Alarming.product.domain.Product;
import com.example.Alarming.product.service.port.ProductRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductUserNotificationHistoryServiceImpl implements ProductUserNotificationHistoryService {

    private final ProductUserNotificationHistoryRepository productUserNotificationHistoryRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductUserNotificationHistory create(Long productId, Long userId) {

        Product product = productRepository.getById(productId);
        ProductUserNotificationHistory productUserNotificationHistory
                = new ProductUserNotificationHistory(productId, userId, product.getRestockRound(), LocalDateTime.now());

        productUserNotificationHistoryRepository.save(productUserNotificationHistory);
        return productUserNotificationHistory;
    }

    @Override
    public ProductUserNotificationHistory getLastProductByProductId(Long productId) {
        return productUserNotificationHistoryRepository.getLastProductByProductId(productId);
    }
}
