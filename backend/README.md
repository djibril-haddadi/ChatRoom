# ChatRooms — Backend

**Spring Boot 3** API for real-time discussion rooms.

## Stack

- Java 17, Spring Boot 3.5  
- Spring Web, Spring Security, Spring Data JPA  
- JWT (`jjwt`), WebSocket / STOMP  
- MySQL  

## Layout

```text
src/main/java/
├── Repositories/                 JPA repositories
└── com/example/demo/
    ├── Controller/               REST endpoints
    ├── Service/                  Business logic
    ├── DTO/                      Request / response models
    ├── Security/                 JWT helpers
    ├── Config/                   Security, CORS, ModelMapper
    └── …                         Entities (User, Salon, Message…)
```

## Configuration

```bash
cp src/main/resources/application.yml.example src/main/resources/application.yml
```

Set MySQL credentials. Do **not** commit a real `application.yml` with secrets.

## Run

```bash
./mvnw spring-boot:run
```

Default port: **2222**.

## API surface (overview)

Controllers expose among others:

- `User` — register, login, profile  
- `Salon` — rooms / members  
- `Message` — room messages  
- `Invitation` — invites between users  
- WebSocket — STOMP config in `StompWebSocketConfig`  

Exact paths live in the `*Controller.java` classes.
