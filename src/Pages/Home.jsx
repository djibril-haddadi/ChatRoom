import { Link } from 'react-router-dom'

export default function Home() {


    return (
        <div>
            <h1>Bienvenue sur l'application de chat</h1>

                <p><Link to="/login">Connectez-vous</Link> ou <Link to="/register">créez un compte</Link>.</p>
            <p><Link to='/salonList'>Consulter la liste des salons</Link></p>
        </div>
    )
}
