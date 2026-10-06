import { useState } from 'react';
import { Navigate } from 'react-router-dom';


import { Routes, Route } from 'react-router-dom'
import { AuthProvider } from './Components/Auth/AuthContext'
import Home from './Pages/Home'
import Login from './Pages/Login'
import Register from './Pages/Register'
import ChatRoom from './Pages/ChatRoom'
import './styles/global.css'

function App() {
    const [user, setUser] = useState(null);
    return (
        <AuthProvider>
            <Routes>  {/* <-- Utilise Routes directement, sans Router */}
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/chat/:id" element={<ChatRoom /> } />
            </Routes>
        </AuthProvider>
    )
}

export default App
