# Frontend — Projet Chat

Interface **React 19 + Vite** pour l’application de salons de discussion.

## Structure

```text
src/
├── components/
│   ├── auth/           AuthContext (JWT + user)
│   └── dashboard/      SalonList
├── pages/              Home, Login, Register, Dashboard, Profile, ChatRoom, NotFound
├── services/api.js     Client Axios + interceptor JWT
├── styles/             CSS global
├── App.jsx             Routes
└── main.jsx            Entrée + BrowserRouter
```

## Configuration

```bash
cp .env.example .env
# VITE_API_URL=http://localhost:2222
```

## Lancer

```bash
npm install
npm run dev
```

Ouvre [http://localhost:5173](http://localhost:5173). Le backend doit tourner sur le port configuré dans `.env`.
