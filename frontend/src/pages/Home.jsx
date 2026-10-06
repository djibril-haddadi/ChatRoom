
import { Link, useNavigate } from 'react-router-dom'
import { useAuth } from '../components/auth/AuthContext'

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
                <h1>ChatRooms</h1>
                {isAuthenticated ? (
                    <div className="user-greeting">
                        <p>Bonjour, {user?.name || 'Utilisateur'} !</p>
                        <button onClick={handleLogout} className="logout-button">Déconnexion</button>
                        <button onClick={() => navigate('/dashboard')} className="dashboard-button">Dashboard</button>
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
                    <h2>Join discussion rooms</h2>
                    <p>Sign in to create rooms, invite others, and chat with your team.</p>
                </section>
            </main>
        </div>
    )
}
