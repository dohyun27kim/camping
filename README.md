# camping

![땡큐캠핑](https://user-images.githubusercontent.com/67825670/209745237-29e8584b-ef60-45f3-aa42-23e2fb2e0ffa.png)

캠핑장 


# 서비스 시나리오

캠핑장 예약 커버하기

기능적 요구사항
기능적 요구사항
1. 캠핑장 관리자가 관리자 사이트에 캠핑장을 관리(등록,수정,삭제) 한다.
2. 고객이 캠핑장을 선택하여, 예약한다.
3. 예약과 동시에 결제가 진행된다.
4. 결제 완료 후, 마일리지가 적립된다.
5. 캠핑장 예약 확정되면 예약 내용이 고객에게 전송된다.
6. 고객이 캠핑장 예약을 취소한다.
7. 캠핑장 예약이 취소되면 마일리지가 차감된다.
8. 캠핑장 예약이 취소되면 취소 내용이 고객에게 전송된다.
9. 캠핑장 후기(review)를 남길 수 있다. 
10. 캠핑장 후기를 남긴 후 마일리지가 적립된다.
11. 캠핑장에 대한 정보 및 예약상태를 확인 할 수 있다.


비기능적 요구사항
1. 트랜잭션
    1. 결제가 되지 않은 예약 건은 아예 거래가 성립되지 않아야 한다  Sync 호출 
1. 장애격리
    1. 캠핑지등록 및 메시지 전송 기능이 수행되지 않더라도 주문은 365일 24시간 받을 수 있어야 한다  Async (event-driven), Eventual Consistency
    1. 예약 시스템이 과중되면 사용자를 잠시동안 받지 않고 결제를 잠시후에 하도록 유도한다  Circuit breaker, fallback
1. 성능
    1. 모든 캠핑지에 대한 정보 및 예약 상태 등을 한번에 확인할 수 있어야 한다.  CQRS
    1. 예약의 상태가 바뀔 때마다 메시지로 알림을 줄 수 있어야 한다.  Event driven



# 분석/설계


## Event Storming 결과
* MSAEz 로 모델링한 이벤트스토밍 결과:  https://labs.msaez.io/#/storming/54a5ae4f559b3df8206b4eeb0d933941


### 이벤트 도출
![image](https://user-images.githubusercontent.com/117260810/209923025-e96128d3-112a-41f8-8863-90fa490ecf45.png)


# 구현:

1. SAGA (PUB/SUB) 4. REQ/RES 테스트 결과

campsite - reservation - payment가 kafka와 req/res 를 타고 변경되는것을 확인함

![image](https://user-images.githubusercontent.com/67825670/209907062-c145229f-07ce-4c04-b7ac-e7a161719043.png)

![image](https://user-images.githubusercontent.com/67825670/209936890-0896f7b3-a1b8-4225-aed7-6fea12f80b1e.png)

![image](https://user-images.githubusercontent.com/67825670/209936943-b0474ba8-634c-47d5-a2b8-8523712a2f2a.png)

![image](https://user-images.githubusercontent.com/67825670/209936997-343c3080-d7a6-4ab5-832f-3f327bb64d0d.png)

![image](https://user-images.githubusercontent.com/67825670/209937040-c7e14972-c5be-48e5-8789-032d5605b075.png)

수정한 소스 코드
![image](https://user-images.githubusercontent.com/117260810/210023860-3b1f7e36-0b79-4c20-baa0-06ba4f9b02c7.png)

----
2. CQRS

![image](https://user-images.githubusercontent.com/67825670/210024717-00c9f254-c025-44ac-a3ba-1f37fbbd0d46.png)
![image](https://user-images.githubusercontent.com/67825670/210024764-56ec3096-3058-41aa-a41b-b88162f8fe0f.png)

----
3. Compensation & Correlation

결제취소시 예약취소,캠프사이트 예약가능으로 변경

![image](https://user-images.githubusercontent.com/67825670/210025571-fee6018c-3677-4f98-8766-16a95a8d0d0c.png)

![image](https://user-images.githubusercontent.com/67825670/210025701-2595f8cb-f7cd-4d14-a5e6-ead74ebaa2a4.png)

![image](https://user-images.githubusercontent.com/67825670/210025719-8bef8630-796d-4a3f-b70c-697c86df8bc8.png)

----

5. Circuit Breaker 
참조 : https://labs.msaez.io/#/courses/cna-full/32c3e5c0-7cd9-11ed-b37b-0b0e73d05d98/circuit-breaker-2022

----
6. Gateway / Ingress
참조 : 


----
7. Deploy / Pipeline
참조 : 


----
8. Autoscale (HPA)

----
9. Zero-downtime deploy (Readiness probe)
참조 : https://labs.msaez.io/#/courses/cna-full/32c3e5c0-7cd9-11ed-b37b-0b0e73d05d98/ops-readiness

----
10. Persistence Volume/ConfigMap/Secret
참조 : https://labs.msaez.io/#/courses/cna-full/32c3e5c0-7cd9-11ed-b37b-0b0e73d05d98/ops-persistence-volume-efs

----
11. Self-healing (liveness probe)

----
12. Loggregation / Monitoring

----
