# ChatRoom — Frontend

**React 19 + Vite** UI for the ChatRoom discussion-rooms app.

## Layout

```text
src/
├── components/
│   ├── auth/           AuthContext (JWT + user)
│   └── dashboard/      SalonList
├── pages/              Home, Login, Register, Dashboard, Profile, ChatRoom, NotFound
├── services/api.js     Axios client + JWT interceptor
├── styles/             Global CSS
├── App.jsx             Routes
└── main.jsx            Entry + BrowserRouter
```

## Configuration

```bash
cp .env.example .env
# VITE_API_URL=http://localhost:2222
```

## Run

```bash
npm install
npm run dev
```

Open [http://localhost:5173](http://localhost:5173). The backend should be running on the port set in `.env`.
