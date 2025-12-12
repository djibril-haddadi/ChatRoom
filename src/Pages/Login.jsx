// src/Pages/Login.jsx
import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { useAuth } from '../Components/Auth/AuthContext'
import api from '../Components/utils/api.jsx'

export default function Login() {
    const [email, setEmail] = useState('')
    const [mdp, setPassword] = useState('')
    const [error, setError] = useState('')
    const { login } = useAuth()
    const navigate = useNavigate()

    const handleSubmit = async (e) => {
        e.preventDefault()
        setError('')

        try {
            const response = await api.post('/login', { email, mdp })
            login(response.data.token, response.data.user)
            navigate('/')
        } catch (err) {
            setError('Email ou mot de passe incorrect')
        }
    }

    return (
        <div className="auth-container">
            <Link to="/" className="back-button">
                Retour à l'accueil
            </Link>

            <h1>Connexion</h1>
            {error && <p className="error-message">{error}</p>}

            <form onSubmit={handleSubmit} className="auth-form">
                <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="mdp">Mot de passe</label>
                    <input
                        type="mdp"
                        id="mdp"
                        value={mdp}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>

                <button type="submit" className="submit-button">Se connecter</button>
            </form>

            <p className="auth-redirect">
                Pas encore de compte ? <Link to="/register">S'inscrire</Link>
            </p>
        </div>
    )
}
