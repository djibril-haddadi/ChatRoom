// src/Pages/Home.jsx
import { Link, useNavigate } from 'react-router-dom'
import { useAuth } from '../Components/Auth/AuthContext'

export default function Home() {
    const { isAuthenticated, logout, user } = useAuth()
    const navigate = useNavigate()

    const handleLogout = () => {
        logout()
        navigate('/login')
    }

    return (
        <div className="home-container">
            <header className="home-header">
                <h1>Bienvenue sur notre application de chat</h1>
                {isAuthenticated ? (
                    <div className="user-greeting">
                        <p>Bonjour, {user?.name || 'Utilisateur'} !</p>
                        <button onClick={handleLogout} className="logout-button">Déconnexion</button>
                    </div>
                ) : (
                    <div className="auth-links">
                        <Link to="/login" className="auth-link">Connexion</Link>
                        <Link to="/register" className="auth-link">Inscription</Link>
                    </div>
                )}
            </header>

            <main className="home-main">
                <section className="home-section">
                    <h2>Rejoignez nos salons de discussion</h2>
                    <p>Connectez-vous pour accéder à nos salons et discuter avec la communauté.</p>
                </section>
            </main>
        </div>
    )
}
