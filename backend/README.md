# ChatRooms — Backend

**Spring Boot 3** API for real-time discussion rooms.

## Stack

- Java 17, Spring Boot 3.5  
- Spring Web, Spring Security, Spring Data JPA  
- JWT (`jjwt`), WebSocket / STOMP  
- MySQL  

## Package layout

```text
com.djibrilhaddadi.chatrooms/
├── ChatRoomsApplication.java
├── config/          WebConfig, StompWebSocketConfig
├── controller/      User, Salon, Message, Invitation, …
├── dto/
├── entity/          User, Salon, Message, Invitation, …
├── repository/
├── security/        JwtTokenProvider
└── service/
```

## Configuration

```bash
cp src/main/resources/application.yml.example src/main/resources/application.yml
```

Set MySQL credentials and a long `app.jwt.secret`. Do **not** commit a real `application.yml` with secrets.

## Run

```bash
./mvnw spring-boot:run
```

Default port: **2222**.

## Main HTTP routes

| Method | Path | Purpose |
|--------|------|---------|
| POST | `/User/add` | Register |
| POST | `/User/login` | Login (returns JWT) |
| GET | `/User/getByEmail` | Profile |
| POST | `/Salon/add` | Create room |
| GET | `/Salon/{email}/creator` | Rooms created by user |
| GET | `/Salon/{email}/member` | Rooms the user belongs to |
| GET | `/Salon/{titre}/messages` | Messages in a room |
| POST | `/Message/add` | Send a message |
| GET | `/Salon/{titre}/user` | Room members |
