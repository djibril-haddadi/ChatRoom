# Backend — Projet Chat

API **Spring Boot 3** pour une application de salons de discussion.

## Stack

- Java 17, Spring Boot 3.5
- Spring Web, Spring Security, Spring Data JPA
- JWT (`jjwt`), WebSocket / STOMP
- MySQL

## Structure

```text
src/main/java/
├── Repositories/                 Accès données (JPA)
└── com/example/demo/
    ├── Controller/               Endpoints REST
    ├── Service/                  Règles métier
    ├── DTO/                      Request / Response
    ├── Security/                 JWT
    ├── Config/                   Security, CORS, ModelMapper
    └── …                         Entités (User, Salon, Message…)
```

## Configuration

```bash
cp src/main/resources/application.yml.example src/main/resources/application.yml
```

Renseigner l’URL MySQL et les identifiants. Ne **jamais** committer `application.yml` avec des secrets.

## Lancer

```bash
./mvnw spring-boot:run
```

Port par défaut : **2222** (voir `application.yml.example`).

## Endpoints (aperçu)

Les contrôleurs exposent notamment :

- `User` — inscription, login, profil
- `Salon` — CRUD / membres
- `Message` — messages de salon
- `Invitation` — invitations entre utilisateurs
- WebSocket — config STOMP dans `StompWebSocketConfig`

Les chemins exacts sont définis dans les classes `*Controller.java`.
