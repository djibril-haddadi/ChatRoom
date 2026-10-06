import { useState, useEffect } from 'react'
import api from '../../services/api'

export default function SalonList() {
  const [salons, setSalons] = useState([])
  const [error, setError] = useState('')

  useEffect(() => {
    const fetchSalons = async () => {
      try {
        const response = await api.get('/getSalons')
        setSalons(response.data)
      } catch (err) {
        setError('Impossible de charger les salons')
        console.error(err)
      }
    }

    fetchSalons()
  }, [])

  if (error) {
    return <p>{error}</p>
  }

  return (
    <div>
      <h1>Liste des salons</h1>
      <ul>
        {salons.map((salon) => (
          <li key={salon.id}>
            <h2>{salon.titre}</h2>
            <p>{salon.description}</p>
          </li>
        ))}
      </ul>
    </div>
  )
}
