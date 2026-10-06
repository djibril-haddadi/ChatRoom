# ChatRooms

Full-stack **real-time discussion rooms** app — Spring Boot API + React (Vite) client, shipped as a monorepo.

Users can register, sign in with JWT, manage rooms from a dashboard, update their profile, and chat inside a room.

---

## Features

| Area | What it does |
|------|----------------|
| Auth | Sign up, login, JWT stored on the client |
| Dashboard | Room list / creation, pagination UI |
| Profile | View and update user information |
| Chat | Room messaging through the API |
| Backend | Users, rooms, messages, invitations, events; STOMP WebSocket support |

**Stack:** Java 17 · Spring Boot 3 · Spring Security · JPA · MySQL · JWT · WebSocket · React 19 · Vite · React Router · Axios

---

## Repository layout

```text
ChatRooms/
├── README.md
├── scripts/git-commit-clean.sh   Helper to commit without tooling trailers
├── backend/
│   ├── pom.xml
│   └── src/main/java/com/djibrilhaddadi/chatrooms/
│       ├── ChatRoomsApplication.java
│       ├── config/          Security, CORS, WebSocket
│       ├── controller/      REST endpoints
│       ├── dto/
│       ├── entity/          User, Salon, Message, …
│       ├── repository/
│       ├── security/        JWT
│       └── service/
└── frontend/
    ├── package.json
    ├── .env.example
    └── src/
        ├── components/      Auth context, shared UI
        ├── pages/           Home, Login, Dashboard, ChatRoom, Profile…
        ├── services/api.js  Axios + JWT interceptor
        └── styles/
```

---

## Requirements

- **Backend:** Java 17+, Maven Wrapper (`./mvnw`), MySQL  
- **Frontend:** Node.js 18+, npm  

---

## Quick start

### 1. Backend

```bash
cd backend
cp src/main/resources/application.yml.example src/main/resources/application.yml
# Edit application.yml: MySQL URL, username, password
./mvnw spring-boot:run
```

API default port: **2222** (configurable in `application.yml`).

### 2. Frontend

```bash
cd frontend
cp .env.example .env
npm install
npm run dev
```

UI: [http://localhost:5173](http://localhost:5173)

---

## Architecture

```text
[ React (Vite) ]  --REST + JWT-->  [ Spring Boot API ]
       |                                |
       |                         [ MySQL / JPA ]
       |                                |
       +---- (realtime chat) --------> [ WebSocket / STOMP ]
```

- **Auth:** login → JWT → `Authorization: Bearer …` on Axios requests  
- **Domain model:** User, Salon (room), Message, Invitation, Evenement, Suppression  
- **Backend layers:** Controller → Service → Repository (+ DTOs)

---

## Useful commands

| Command | Where | Purpose |
|---------|--------|---------|
| `./mvnw spring-boot:run` | `backend/` | Run the API |
| `./mvnw test` | `backend/` | Run tests |
| `npm run dev` | `frontend/` | Vite dev server |
| `npm run build` | `frontend/` | Production build |
| `npm run lint` | `frontend/` | ESLint |

---

## Author

**Djibril Haddadi** — [github.com/djibril-haddadi](https://github.com/djibril-haddadi)
