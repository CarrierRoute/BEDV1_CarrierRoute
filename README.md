# CarrierRoute
``` 너는 캐리어만 챙겨, 항공권&호텔&렌트카 예약 서비스 🧳  ```

## ✋ 프로젝트 개요

CarrierRoute는 [SkyScanner](https://www.skyscanner.co.kr/) 서비스를 클론코딩하는 프로젝트입니다.  
여행에 필요한 항공권, 호텔, 렌트카를 조회하고 예약하는 API를 구현하였습니다.

## 💻 기술 스택

### 언어
![Java](https://img.shields.io/badge/java-007396.svg?style=for-the-badge&logo=java&logoColor=white)

### 백엔드
![Spring](https://img.shields.io/badge/springboot-6DB33F.svg?style=for-the-badge&logo=springBoot&logoColor=white)
![Spring](https://img.shields.io/badge/spring_jpa-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-232F3E.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)

### 데이터베이스
![H2](https://img.shields.io/badge/H2-124D97.svg?style=for-the-badge&logo=H2&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=MySQL&logoColor=white)

### 테스팅
![GitHub](https://img.shields.io/badge/JUnit-25A162.svg?style=for-the-badge&logo=JUnit5&logoColor=white)

### 협업
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000.svg?style=for-the-badge&logo=notion&logoColor=white)

## 📖 API 목록

### 항공권 관련 API
|API|Http Method|URI|
|------|---|---|
|항공권 전체 조회|GET|/api/v1/flights/|
|항공편 전체 조회|GET|/api/v1/flights/|
|항공편 단일 조회|GET|/api/v1/flights/{flightId}|
|항공편 예약|POST|/api/v1/bookings/flights|
|항공편 예약 조회|GET|/api/v1/bookings/flights|
|항공편 예약 상세조회|GET|/api/v1/bookings/flights/{bookingId}|
|항공편 예약 취소|DELETE|/api/v1/bookings/flights/{bookingId}|

### 호텔 관련 API
|API|Http Method|URI|
|------|---|---|
|호텔 검색|GET|/api/v1/hotels
|호텔 상세 조회|GET|/api/v1/hotels/{hotelId}
|호텔 예약|POST|/api/v1/bookings/hotels
|호텔 예약 조회|GET|/api/v1/bookings/hotels
|호텔 예약 상세조회|GET|/api/v1/bookings/hotels/{bookingId}|
|호텔 예약 취소|DELETE|/api/v1/bookings/hotels/{bookingId}|

### 렌트카 관련 API
|API|Http Method|URI|
|------|---|---|
|렌트카 검색|GET|/api/v1/cars|
|렌트카 예약|POST|/api/v1/bookings/cars|
|렌트카 예약 조회|GET|/api/v1/bookings/cars|
|렌트카 예약 상세 조회|GET|/api/v1/bookings/cars/{bookingId}|
|렌트카 예약 취소|DELETE|/api/v1/bookings/cars/{bookingId}|

## 👬 팀원 소개

|[김진아](https://github.com/nasaoreo)|[이태현](https://github.com/neilsonT)|[허승연](https://github.com/heoseungyeon)|
|:---:|:---:|:---:|
|프로덕트 오너 & 개발자|개발 리더 & 개발자|스크럼 마스터 & 개발자|
|![julie](./images/julie_profile.png)|![henry](./images/henry_profile.png)|![eddie](./images/eddie_profile.png)|
