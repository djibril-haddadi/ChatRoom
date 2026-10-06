
import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { useAuth } from '../components/auth/AuthContext'
import api from '../services/api'

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
            const response = await api.post('/User/login', { email, mdp })
            const token = response.data.token

            try {
                // Récupérer les infos utilisateur pour alimenter le profil
                const userResponse = await api.get('/User/getByEmail', {
                    params: { email },
                    headers: { Authorization: `Bearer ${token}` },
                })

                login(token, userResponse.data)
                navigate('/dashboard')
            } catch (fetchErr) {
                setError( 'Impossible de charger le profil après connexion')
            }
        } catch (err) {
            setError( 'Email ou mot de passe incorrect')
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
                        type="password"
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