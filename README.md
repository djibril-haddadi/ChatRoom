# projet-chat

Salon de discussion (projet UTC AI13) — monorepo **backend Spring Boot** + **frontend React (Vite)**.

## Structure

```
backend/    API Spring Boot (Java / Maven)
frontend/   Interface React + Vite
```

Historique Git conservé via `git subtree` depuis les dépôts GitLab d’origine.

## Prérequis

- **Backend** : Java 17+, Maven (ou `./mvnw`)
- **Frontend** : Node.js 18+, npm

## Lancer en local (aperçu)

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

L’API démarre en général sur le port configuré dans `backend/src/main/resources` (souvent `8080`).

### Frontend

```bash
cd frontend
npm install
npm run dev
```

Vite sert l’UI en local (souvent `http://localhost:5173`). Configurez l’URL de l’API selon le README / les variables du frontend.

## Remotes

- `origin` — GitHub (après transfert)
- `gitlab-backend` / `gitlab-frontend` — dépôts GitLab d’origine (référence uniquement)

## Auteur

djibril-haddadi
