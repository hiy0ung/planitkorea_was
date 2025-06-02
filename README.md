## 🔴 프로젝트 개요
# PlanItKorea_was
코리아아이티아카데미 부산  
**[산대특] IoT 기반 웹솔루션 개발자 과정**  
Back-end 프로젝트  

## 🔵 REST API 명세서
### 링크
배포 링크  

## 🟢 주요 기능
- 지역, 예약 날짜, 인원수 등 다양한 조건에 맞춘 숙소 검색 및 필터링
- 실시간 예약 가능 여부 확인 및 예약 내역 관리
- 숙소 리뷰 작성 및 조회, 예약 내역 조회 및 취소 기능
- 관심 숙소 추가/삭제 및 조회하는 위시리스트 기능
- 고객 지원을 위한 1:1 문의 및 공지사항, 자주 묻는 질문 조회 기능 

## 🟠 담당 기능
- JWT를 이용한 보안 및 인증 시스템 구축
- 회원가입, 로그인 기능 구현
- 검색 조건에 맞는 숙소 조회 기능 구현
- 인기 여행지 숙소 조회 기능 구현
- 특정 숙소 내 예약 가능 객실 조회 기능 구현
- 위시리스트 추가, 조회 삭제 기능 구현
- 1:1 문의 생성, 조회, 수정, 삭제 기능 구현

## 🟡 기술 스택
- Java 17
- Spring boot 3.2.0
- Spring web
- Spring validation (Jakarta Validation)
- Spring data JPA
- Spring security
- Spring oauth2 client
- Spring Mail
- JJWT 0.11.2
- MySQL Connector
- Lombok

## 🟣 프로젝트 실행 방법
### 클론 및 폴더 이동
```bash
git https://github.com/hiy0ung/planitkorea_was.git
cd planitkorea_was
```

### 빌드 방법
```bash
./gradlew clean build
```

## 📁 폴더 구조
```md
📂 planitkorea_was
├─ 📂 src
│  └─ 📂 main/java/org.koreait.planitkorea
│           └─ 📂 common
│           └─ 📂 config
│           └─ 📂 controller
│           └─ 📂 dto
│           └─ 📂 entity
│           └─ 📂 filter
│           └─ 📂 handler
│           └─ 📂 provider
│           └─ 📂 repository
│           └─ 📂 scheduler
│           └─ 📂 service
│  └─ 📂 test
├─ 📂 gradle
├─ 📂 image
├─ 📃 build.gradle
└─ 💻 gradlew
```