import React, { useEffect, useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { useAuth } from '../components/auth/AuthContext'
import api from '../services/api'

const initialState = {
    email: '',
    nom: '',
    prenom: '',
    pseudo: '',
    active: true,
}

const Profile = () => {
    const { user, token, isAuthenticated, updateUser } = useAuth()
    const [form, setForm] = useState(initialState)
    const [loading, setLoading] = useState(true)
    const [saving, setSaving] = useState(false)
    const [message, setMessage] = useState('')
    const [error, setError] = useState('')
    const navigate = useNavigate()

    // Charger les données utilisateur depuis l'API
    useEffect(() => {
        if (!isAuthenticated) {
            setLoading(false)
            navigate('/login')
            return
        }
        const email = user?.email
        if (!email) {
            setError("Email utilisateur introuvable. Veuillez vous reconnecter.")
            setLoading(false)
            return
        }

        const fetchProfile = async () => {
                    try {
                        const res = await api.get('/User/getByEmail', {
                            params: { email },
                            headers: token ? { Authorization: `Bearer ${token}` } : {},
                        })
                        const next = { ...initialState, ...res.data }
                        setForm(next)
                        updateUser(next)
                        setError('')
                    } catch (err) {
                        setError('Impossible de charger le profil')
                    } finally {
                        setLoading(false)
                    }
                }


        fetchProfile()
    }, [isAuthenticated, user?.email, token, navigate, updateUser])

    const handleChange = (e) => {
        const { name, value } = e.target
        setForm((prev) => ({ ...prev, [name]: value }))
    }

    const refreshProfile = async (emailParam) => {
            const res = await api.get('/User/getByEmail', {
                params: { email: emailParam },
                headers: token ? { Authorization: `Bearer ${token}` } : {},
            })
            const next = { ...initialState, ...res.data }
            setForm(next)
            updateUser(next)
            return next
        }

        const toggleAccount = async () => {
            const nextActive = !form.active
            const emailParam = form.email
            try {
                await api.put(`/User/${encodeURIComponent(emailParam)}/active`, null, {
                    params: { active: nextActive },
                    headers: token ? { Authorization: `Bearer ${token}` } : {},
                })
                // Recharger depuis le backend pour s'assurer de l'état réel
                const updated = await refreshProfile(emailParam)
                setMessage(updated.active ? 'Compte réactivé' : 'Compte désactivé')
                setError('')
            } catch (err) {
                setError( 'Échec de la mise à jour du statut')
                setMessage('')
            }
        }

    const saveProfile = async () => {
        setSaving(true)
        setMessage('')
        try {
            await api.put('/User/modify', {
                email: form.email,
                nom: form.nom,
                prenom: form.prenom,
                pseudo: form.pseudo,
            })
            updateUser(form)
            setMessage('Profil sauvegardé')
            setError('')
        } catch (err) {
            setError("Erreur lors de l'enregistrement")
        } finally {
            setSaving(false)
        }
    }

    if (loading) {
        return <p style={{ textAlign: "center", marginTop: "20px" }}>Chargement du profil...</p>
    }

    return (
        <div style={{ padding: "20px", maxWidth: "600px", margin: "0 auto" }}>
            <h2>👤 Mon Profil</h2>

            <div style={{ border: "1px solid #ddd", padding: "20px", borderRadius: "8px" }}>
                {error && <p style={{ color: "#dc3545" }}>{error}</p>}
                {message && <p style={{ color: "#28a745" }}>{message}</p>}

                <div style={{ marginBottom: "15px" }}>
                    <label>Nom :</label>
                    <input
                        type="text"
                        name="nom"
                        value={form.nom || ''}
                        onChange={handleChange}
                        style={{ display: "block", width: "100%", padding: "8px", marginTop: "5px" }}
                    />
                </div>

                <div style={{ marginBottom: "15px" }}>
                    <label>Prénom :</label>
                    <input
                        type="text"
                        name="prenom"
                        value={form.prenom || ''}
                        onChange={handleChange}
                        style={{ display: "block", width: "100%", padding: "8px", marginTop: "5px" }}
                    />
                </div>

                <div style={{ marginBottom: "15px" }}>
                    <label>Pseudo :</label>
                    <input
                        type="text"
                        name="pseudo"
                        value={form.pseudo || ''}
                        onChange={handleChange}
                        style={{ display: "block", width: "100%", padding: "8px", marginTop: "5px" }}
                    />
                </div>

                <div style={{ marginBottom: "15px" }}>
                    <label>Email (identifiant) :</label>
                    <input
                        type="email"
                        name="email"
                        value={form.email || ''}
                        readOnly
                        style={{ display: "block", width: "100%", padding: "8px", marginTop: "5px", backgroundColor: "#f6f6f6" }}
                    />
                </div>

                <div style={{ marginTop: "20px", paddingTop: "20px", borderTop: "1px solid #eee" }}>
                    <label>État du compte : </label>
                    <span style={{ fontWeight: "bold", color: form.active ? "green" : "red", marginLeft: "5px" }}>
                        {form.active ? "ACTIF" : "DÉSACTIVÉ"}
                    </span>
                    <br /><br />

                    <button
                        onClick={toggleAccount}
                        style={{
                            backgroundColor: form.active ? "#dc3545" : "#28a745",
                            color: "white", padding: "10px", border: "none", borderRadius: "5px", cursor: "pointer"
                        }}
                    >
                        {form.active ? "Désactiver mon compte" : "Réactiver mon compte"}
                    </button>

                    <button
                        onClick={saveProfile}
                        disabled={saving}
                        style={{
                            marginLeft: "10px",
                            padding: "10px",
                            backgroundColor: "#007bff",
                            color: "white",
                            border: "none",
                            borderRadius: "5px",
                            cursor: "pointer",
                            opacity: saving ? 0.7 : 1,
                        }}
                    >
                        {saving ? "Sauvegarde..." : "Sauvegarder les modifications"}
                    </button>
                </div>
                <Link to="/dashboard" className="back-button">Retour au dashboard</Link>
            </div>
        </div>
    )
}

export default Profile