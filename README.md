# api-server

Spring Boot 기반 API 서버입니다.

## 사전 요구 사항

- Java 17
- Maven

## 빌드 및 실행 방법

### 빌드

```bash
./mvnw clean package
```

### 실행

```bash
java -jar target/api-server-0.0.1-SNAPSHOT.jar
```

## API 엔드포인트

서버는 다음 엔드포인트를 제공합니다:

- `GET /`: "It works!" 메시지를 반환합니다.
- `GET /hello`: 서비스 레이어로부터 "Hello" 메시지를 반환합니다.

### API 문서

API 문서는 Swagger UI를 통해 확인할 수 있습니다. 애플리케이션 실행 후 다음 주소로 접속하세요:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
