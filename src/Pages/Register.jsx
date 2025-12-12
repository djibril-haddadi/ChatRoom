// src/Pages/Register.jsx
import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import api from '../Components/utils/api.jsx'

export default function Register() {
    const [name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [error, setError] = useState('')
    const navigate = useNavigate()

    const handleSubmit = async (e) => {
        e.preventDefault()
        setError('')

        try {
            await api.post('/auth/register', { name, email, password })
            navigate('/login')
        } catch (err) {
            setError('Erreur lors de l\'inscription. Vérifiez vos informations.')
        }
    }

    return (
        <div className="auth-container">
            <Link to="/" className="back-button">
                Retour à l'accueil
            </Link>

            <h1>Inscription</h1>
            {error && <p className="error-message">{error}</p>}

            <form onSubmit={handleSubmit} className="auth-form">
                <div className="form-group">
                    <label htmlFor="name">Nom</label>
                    <input
                        type="text"
                        id="name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                </div>

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
                    <label htmlFor="password">Mot de passe</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>

                <button type="submit" className="submit-button">S'inscrire</button>
            </form>

            <p className="auth-redirect">
                Déjà un compte ? <Link to="/login">Se connecter</Link>
            </p>
        </div>
    )
}
