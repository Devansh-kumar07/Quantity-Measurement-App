# QMA Microservices Architecture

## Services Overview

| Service             | Port | Responsibility                          |
|---------------------|------|-----------------------------------------|
| eureka-server       | 8761 | Service Discovery Dashboard             |
| user-service        | 8081 | Login, Register, Google OAuth2          |
| measurement-service | 8082 | Compare, Convert, Add, Subtract, Divide |
| history-service     | 8083 | Save & Fetch operation history          |
| api-gateway         | 8080 | Single entry point for all requests     |

---

## STEP 1 — application.properties update karo

### user-service/src/main/resources/application.properties
```
spring.datasource.username=TUMHARA_DB_USERNAME
spring.datasource.password=TUMHARA_DB_PASSWORD
GOOGLE_CLIENT_ID=tumhari_google_client_id
GOOGLE_CLIENT_SECRET=tumhara_google_client_secret
```

### history-service/src/main/resources/application.properties
```
spring.datasource.username=TUMHARA_DB_USERNAME
spring.datasource.password=TUMHARA_DB_PASSWORD
```

---

## STEP 2 — Environment Variables set karo (Windows)

```cmd
set GOOGLE_CLIENT_ID=your_client_id_here
set GOOGLE_CLIENT_SECRET=your_client_secret_here
```

---

## STEP 3 — Start Order (IMPORTANT - Is order mein hi start karo)

```
1. PostgreSQL database
2. eureka-server       (wait for: "Started EurekaServerApplication")
3. user-service        (wait for: "Started UserServiceApplication")
4. measurement-service (wait for: "Started MeasurementServiceApplication")
5. history-service     (wait for: "Started HistoryServiceApplication")
6. api-gateway         (wait for: "Started ApiGatewayApplication")
7. VS Code Live Server (frontend/index.html)
```

Each service ke liye IntelliJ mein run karo ya:
```bash
cd eureka-server && mvn spring-boot:run
```

---

## STEP 4 — Eureka Dashboard check karo

Browser mein kholo: http://localhost:8761

Ye services registered dikhni chahiye:
- USER-SERVICE
- MEASUREMENT-SERVICE
- HISTORY-SERVICE
- API-GATEWAY

---

## API Flow

```
Frontend (5500)
    ↓
API Gateway (8080)
    ↓
┌─────────────────────────────────────┐
│  /auth/**      → user-service       │
│  /oauth2/**    → user-service       │
│  /login/**     → user-service       │
│  /api/v1/**    → measurement-service│
│  /history/**   → history-service   │
└─────────────────────────────────────┘
```

---

## JWT Secret

IMPORTANT: Teeno services mein SAME jwt.secret hona chahiye:
- user-service
- measurement-service
- history-service

Current value: `quantityMeasurementAppSecretKeyForOAuth2JWT2024LongEnough`

---

## Google OAuth2 Callback

Google Cloud Console mein ye URI add karo:
- Authorized redirect URI: `http://localhost:8080/login/oauth2/code/google`
  (8081 nahi — ab 8080 gateway se jaata hai)
