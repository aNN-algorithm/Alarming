package com.example.Alarming.notification.productUserNotification.service;

import org.springframework.stereotype.Service;

@Service
public class ProductUserNotificationServiceImpl implements ProductUserNotificationService {

    @Override
    public void notify(Long productId) {
        // 해당 상품을 재입고 알람 신청한 사람들 중
        // 수정 날짜 오름차순, 활성화 여부를 가져옴
    }
}