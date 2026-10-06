import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import api from '../services/api'

export default function Register() {
    const [nom, setNom] = useState('');
    const [prenom, setPrenom] = useState('');
    const [pseudo, setPseudo] = useState('');
    const [email, setEmail] = useState('');
    const [mdp, setMdp] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        try {
            await api.post('/User/add', { nom, prenom, pseudo, email, mdp });
            navigate('/login');
        } catch (err) {
            setError('Erreur lors de l\'inscription. Vérifiez vos informations.');
        }
    };

    return (
        <div className="auth-container">
            <Link to="/" className="back-button">
                Retour à l'accueil
            </Link>

            <h1>Inscription</h1>
            {error && <p className="error-message">{error}</p>}

            <form onSubmit={handleSubmit} className="auth-form">
                <div className="form-group">
                    <label htmlFor="nom">Nom</label>
                    <input
                        type="text"
                        id="nom"
                        value={nom}
                        onChange={(e) => setNom(e.target.value)}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="prenom">Prénom</label>
                    <input
                        type="text"
                        id="prenom"
                        value={prenom}
                        onChange={(e) => setPrenom(e.target.value)}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="pseudo">Pseudo</label>
                    <input
                        type="text"
                        id="pseudo"
                        value={pseudo}
                        onChange={(e) => setPseudo(e.target.value)}
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
                    <label htmlFor="mdp">Mot de passe</label>
                    <input
                        type="password"
                        id="mdp"
                        value={mdp}
                        onChange={(e) => setMdp(e.target.value)}
                        required
                    />
                </div>

                <button type="submit" className="submit-button">S'inscrire</button>
            </form>

            <p className="auth-redirect">
                Déjà un compte ? <Link to="/login">Se connecter</Link>
            </p>
        </div>
    );
}
