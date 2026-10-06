# Projet Chat

Application full-stack de **salons de discussion** en temps réel — projet universitaire (UTC, AI13).

Backend **Spring Boot** (REST + JWT + WebSocket) et frontend **React / Vite**, organisés en monorepo.

---

## Démo des fonctionnalités

| Zone | Ce que fait l’app |
|------|-------------------|
| Auth | Inscription, connexion, JWT stocké côté client |
| Dashboard | Liste / création de salons, pagination |
| Profil | Consultation et mise à jour des infos utilisateur |
| Chat | Salon de discussion (messages via l’API) |
| Backend | Users, salons, messages, invitations, événements ; WebSocket STOMP |

Stack : **Java 17 · Spring Boot 3 · Spring Security · JPA · MySQL · JWT · WebSocket** · **React 19 · Vite · React Router · Axios**

---

## Structure du dépôt

```text
projet-chat/
├── README.md                 ← tu es ici
├── backend/                  Spring Boot (API + sécurité + WebSocket)
│   ├── pom.xml
│   ├── src/main/java/...
│   └── src/main/resources/
└── frontend/                 React + Vite
    ├── package.json
    ├── .env.example
    └── src/
        ├── components/       UI réutilisable (auth, dashboard)
        ├── pages/            Écrans (Home, Login, Dashboard, Chat…)
        ├── services/         Client HTTP (Axios)
        └── styles/
```

Chemins frontend stables et en minuscules (`components/`, `pages/`, `services/`) pour rester lisibles sur GitHub et en CI.

---

## Prérequis

- **Backend** : Java 17+, Maven Wrapper (`./mvnw`), MySQL
- **Frontend** : Node.js 18+, npm

---

## Démarrage rapide

### 1. Backend

```bash
cd backend
cp src/main/resources/application.yml.example src/main/resources/application.yml
# Éditer application.yml : URL MySQL, user, password
./mvnw spring-boot:run
```

L’API écoute par défaut sur le port **2222** (configurable dans `application.yml`).

### 2. Frontend

```bash
cd frontend
cp .env.example .env
npm install
npm run dev
```

UI en local : [http://localhost:5173](http://localhost:5173)

---

## Architecture (vue recruteur)

```text
[ React (Vite) ]  --REST + JWT-->  [ Spring Boot API ]
       |                                |
       |                         [ MySQL / JPA ]
       |                                |
       +------ (chat temps réel) -----> [ WebSocket / STOMP ]
```

- **Auth** : login → JWT → header `Authorization: Bearer …` sur les appels Axios  
- **Domaines** : User, Salon, Message, Invitation, Evenement, Suppression  
- **Couches backend** : Controller → Service → Repository (+ DTO)

---

## Points d’attention (honnêteté projet étudiant)

Ce dépôt reflète un **projet de cours** évolutif :

- Le package Java s’appelle encore `com.example.demo` (héritage du starter Spring).
- Certaines parties du dashboard côté front utilisent encore des données d’exemple pour la pagination UI ; l’API salons / auth / profil est branchée sur le backend.
- Pas de déploiement cloud fourni ici : focus sur le code et l’architecture locale.

Ces points n’empêchent pas de comprendre la stack et la structure du projet.

---

## Scripts utiles

| Commande | Où | Rôle |
|----------|----|------|
| `./mvnw spring-boot:run` | `backend/` | Lancer l’API |
| `./mvnw test` | `backend/` | Tests |
| `npm run dev` | `frontend/` | Dev server Vite |
| `npm run build` | `frontend/` | Build production |
| `npm run lint` | `frontend/` | ESLint |

---

## Auteur

**Djibril Haddadi** — [github.com/djibril-haddadi](https://github.com/djibril-haddadi)

Projet issu des dépôts GitLab UTC `ai13_projet_chat` (backend + frontend), regroupés ici pour le portfolio.
