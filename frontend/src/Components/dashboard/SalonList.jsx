import { useState, useEffect } from 'react'

export default function SalonList() {
    // État local pour stocker la liste des salons
    const [salons, setSalons] = useState([])

    // Récupérer les salons depuis le backend au chargement du composant
    useEffect(() => {
        const fetchSalons = async () => {
            try {
                const response = await fetch('http://localhost:2222/getSalons')
                if (!response.ok) {
                    throw new Error('Erreur lors de la récupération des salons')
                }
                const data = await response.json()
                setSalons(data)
            } catch (error) {
                console.error('Erreur:', error)
            }
        }

        fetchSalons()
    }, []) // Le tableau vide signifie que ce code s'exécute une seule fois au montage

    return (
        <div>
            <h1>Liste des salons</h1>
            <ul>
                {salons.map(salon => (
                    <li key={salon.id}>
                        <h2>{salon.titre}</h2>
                        <p>{salon.description}</p>
                    </li>
                ))}
            </ul>
        </div>
    )
}
