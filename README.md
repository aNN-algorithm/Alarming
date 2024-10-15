![header](https://capsule-render.vercel.app/api?type=rounded&color=auto&height=100&section=header&text=Alarming&fontSize=70)   
**재입고 알림 전송 시스템 백엔드 파트 구현**   
: 상품 재입고 시, 재입고 알림 신청한 사람에게 알림을 전송하고, 해당 히스토리 관리

# ✔️ 요구사항
- 재입고 시 상품의 재고 수량, 재입고 회차 업데이트
- 해당 상품 재입고를 신청한 사람에게 알림 전송
  - 해당 상품의 상태를 고려
  - 신청은 알림 신청(재신청)한 순서 -> 수정날짜 기준
- 알림 전송 후 히스토리 테이블에 기록

# ✔️ 시스템 구조도
<img width="700" alt="image" src="https://github.com/user-attachments/assets/cb87f248-4205-4975-a853-f0c8f5f5c27e">

## NotificationManager   
> 재입고 시 `NotificationManager`의 `update` 동작
> - 알림 전송할 상품의 상태(`IN_PROGRESS`)를 `ConcurrentHashMap`에 저장
> - 해당 상품을 구독한 사람들을 알림 대기 큐`notificationWaiting`에 등록하기 위해 `notification.create` 호출
> 품절 시 `NotificationManager`의 `soldout` 동작
> - 현재 `map`에 존재하는 상품의 상태를 변경(`SOLD_OUT`)

## NotificationWaiting
> 해당 상품을 구독한 사람들을 `list`형태로 DB테이블(**ProductUserNotificationRepository**, 상품고객알림)에서 가져와 반환
> 가져온 `list`에 존재하는 마지막 객체에 마지막 객체인 것을 표시
> `productUserNotification.endNotification(true);`
> 해당 `list`를 `notificationWaiting`에 등록

## NotificationSender
> Sender는 Manager가 스프링 빈에 등록 시 스레드 생성 및 동작
> 1초에 500번씩 알림 전송(DB테이블(**ProductUserNotificationHistoryRepository**, 상품고객알림히스토리) 업데이트)
> 마지막 알림일 경우
> - 전송 완료: _COMPLETED_표시 후 DB테이블(**ProductNotificationHistory**, 상품알림히스토리)에 업데이트
> - 품절: _CANCELED_BY_SOLD_OUT_표시 후 DB테이블(**ProductNotificationHistory**, 상품알림히스토리)에 업데이트
>   - 이후에 이 상품에 대한 알림을 보내지 않도록 `ConcurrentHashMap`에 해당 상품의 상태 변경
> - 마지막 알림까지 전송되면 `ConcurrentHashMap`에서 제거
> 1초에 500번을 보내지 못하고 버킷이 넘칠 경우, 알림을 보내지 못한 해당 객체는 다시 대기 큐`notificationWaiting` 맨앞에 등록

# ✔️ 기술 스택
<div align=left> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
</div>


