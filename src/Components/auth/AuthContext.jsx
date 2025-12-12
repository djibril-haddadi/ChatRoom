// src/Components/Auth/AuthContext.jsx
import { createContext, useContext, useState, useEffect } from 'react'

// 1. Créer le contexte
const AuthContext = createContext()

// 2. Fournir le contexte aux enfants
export function AuthProvider({ children }) {
    const [user, setUser] = useState(null)
    const [token, setToken] = useState(localStorage.getItem('token') || null)

    // Sauvegarder le token dans localStorage à chaque changement
    useEffect(() => {
        if (token) {
            localStorage.setItem('token', token)
        } else {
            localStorage.removeItem('token')
        }
    }, [token])

    // Fonction pour connecter l'utilisateur
    const login = (newToken, userData) => {
        setToken(newToken)
        setUser(userData)
    }

    // Fonction pour déconnecter l'utilisateur
    const logout = () => {
        setToken(null)
        setUser(null)
    }

    // Vérifier si l'utilisateur est connecté
    const isAuthenticated = !!token

    return (
        <AuthContext.Provider value={{ user, token, isAuthenticated, login, logout }}>
            {children}
        </AuthContext.Provider>
    )
}

// 3. Hook personnalisé pour utiliser le contexte
export function useAuth() {
    return useContext(AuthContext)
}
