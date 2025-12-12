// src/Components/Auth/api.js
import axios from 'axios'

// Créer une instance d'axios avec la base URL de ton backend Spring
const api = axios.create({
    baseURL: 'http://localhost:8080/api', // URL de ton backend Spring
})

// Ajouter le token JWT à chaque requête si l'utilisateur est connecté
api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token')
    if (token) {
        config.headers.Authorization = `Bearer ${token}`
    }
    return config
})

export default api
