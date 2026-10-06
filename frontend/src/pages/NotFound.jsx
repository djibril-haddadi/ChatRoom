import { Link } from 'react-router-dom'

export default function NotFound() {
  return (
    <div className="home-container" style={{ textAlign: 'center', padding: '4rem 1rem' }}>
      <h1>404</h1>
      <p>Cette page n&apos;existe pas.</p>
      <Link to="/" className="auth-link">
        Retour à l&apos;accueil
      </Link>
    </div>
  )
}
