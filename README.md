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


# 구현/운영

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

----
6. Gateway / Ingress

![image](https://user-images.githubusercontent.com/117251808/210033803-44c05079-7c4b-447c-8ed5-6d4946c3ced3.png)

![image](https://user-images.githubusercontent.com/117251808/210033782-e8e8bbda-5544-4588-bd87-6c05c6bef2fd.png)

----
7. Deploy / Pipeline -> Deploy

![image](https://user-images.githubusercontent.com/67825670/210027247-2fac6f64-64fb-4ebe-82d2-1e65584dbe7c.png)

![image](https://user-images.githubusercontent.com/67825670/210027299-4e07375e-5479-4cd4-aa36-79dcdf6cc419.png)


----
8. Autoscale (HPA)
 
![image](https://user-images.githubusercontent.com/67825670/210035482-9eb7e89b-c8e0-4583-bf0e-a3d7d7d47f7f.png)

![image](https://user-images.githubusercontent.com/67825670/210037015-714764d4-c564-4f74-9c80-50cca3eeb2fd.png)

![image](https://user-images.githubusercontent.com/67825670/210037039-b92be727-08ec-45b1-af54-f8ffb65f5318.png)

![image](https://user-images.githubusercontent.com/67825670/210037054-0e0cfa67-41cf-4073-8d89-b3fa16a2faf4.png)

![image](https://user-images.githubusercontent.com/67825670/210037418-0f3c8bf4-cd00-4060-9ee2-f77b4c0282ae.png)

![image](https://user-images.githubusercontent.com/67825670/210037440-95eaa51f-60ba-4505-9c9a-f64c8a501852.png)
 


----
9. Zero-downtime deploy (Readiness probe)

![image](https://user-images.githubusercontent.com/67825670/210039827-06371dfe-7fc2-49f6-9235-936fd5c6cbac.png)

![image](https://user-images.githubusercontent.com/67825670/210039854-0a0b1a5c-317e-40d4-81f0-ff32e8d0bd75.png)

![image](https://user-images.githubusercontent.com/67825670/210039882-36608b00-a8ea-402a-9100-a997011a9f0a.png)



----
10. Persistence Volume/ConfigMap/Secret

![image](https://user-images.githubusercontent.com/67825670/210042798-2754b899-e298-40a1-b924-aebc484a20a5.png)

![image](https://user-images.githubusercontent.com/67825670/210042854-1cfd765c-373b-4af1-88a6-bd1d9b188c7c.png)

![image](https://user-images.githubusercontent.com/67825670/210042905-75384083-8045-4b83-90f2-a75d8ef834cf.png)


----
11. Self-healing (liveness probe)

![image](https://user-images.githubusercontent.com/67825670/210038796-a5fcd1a2-45e5-4eba-9ec6-e6d940546bfb.png)

![image](https://user-images.githubusercontent.com/67825670/210038822-0cd9f705-c921-4c5a-a371-e9db1e63a869.png)

![image](https://user-images.githubusercontent.com/67825670/210038846-4670c678-f77e-4838-a72a-672555d79f29.png)




----
12. Loggregation / Monitoring

----
