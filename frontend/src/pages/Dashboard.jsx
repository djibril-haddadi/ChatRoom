import { useCallback, useEffect, useMemo, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useAuth } from '../components/auth/AuthContext'
import api from '../services/api'

const ITEMS_PER_PAGE = 3

function paginate(items, page) {
  const totalPages = Math.max(1, Math.ceil(items.length / ITEMS_PER_PAGE))
  const safePage = Math.min(page, totalPages)
  const start = (safePage - 1) * ITEMS_PER_PAGE
  return {
    pageItems: items.slice(start, start + ITEMS_PER_PAGE),
    totalPages,
    page: safePage,
  }
}

export default function Dashboard() {
  const navigate = useNavigate()
  const { user, isAuthenticated, logout } = useAuth()

  const [newRoomName, setNewRoomName] = useState('')
  const [newRoomDescription, setNewRoomDescription] = useState('')
  const [myRooms, setMyRooms] = useState([])
  const [memberRooms, setMemberRooms] = useState([])
  const [pageMyRooms, setPageMyRooms] = useState(1)
  const [pageMemberRooms, setPageMemberRooms] = useState(1)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [info, setInfo] = useState('')

  const loadRooms = useCallback(async () => {
    if (!user?.email) return
    setLoading(true)
    setError('')
    try {
      const [createdRes, memberRes] = await Promise.all([
        api.get(`/Salon/${encodeURIComponent(user.email)}/creator`),
        api.get(`/Salon/${encodeURIComponent(user.email)}/member`),
      ])
      setMyRooms(createdRes.data || [])
      setMemberRooms(memberRes.data || [])
    } catch (err) {
      console.error(err)
      setError('Unable to load rooms from the API.')
    } finally {
      setLoading(false)
    }
  }, [user?.email])

  useEffect(() => {
    if (!isAuthenticated) {
      navigate('/login')
      return
    }
    loadRooms()
  }, [isAuthenticated, navigate, loadRooms])

  const myPage = useMemo(() => paginate(myRooms, pageMyRooms), [myRooms, pageMyRooms])
  const memberPage = useMemo(
    () => paginate(memberRooms, pageMemberRooms),
    [memberRooms, pageMemberRooms]
  )

  const openRoom = (titre) => {
    navigate(`/chat/${encodeURIComponent(titre)}`)
  }

  const handleCreateRoom = async (e) => {
    e.preventDefault()
    if (!newRoomName.trim() || !user?.email) return
    setError('')
    setInfo('')
    try {
      await api.post('/Salon/add', {
        titre: newRoomName.trim(),
        description: newRoomDescription.trim() || 'New room',
        creatorEmail: user.email,
      })
      setNewRoomName('')
      setNewRoomDescription('')
      setInfo('Room created.')
      await loadRooms()
    } catch (err) {
      console.error(err)
      setError('Could not create the room.')
    }
  }

  const handleEdit = async (room) => {
    const newDescription = window.prompt('New room description:', room.description || '')
    if (newDescription === null) return
    try {
      await api.put('/Salon/modify', {
        titre: room.titre,
        description: newDescription,
        creatorEmail: user.email,
      })
      await loadRooms()
      setInfo('Room updated.')
    } catch (err) {
      console.error(err)
      setError('Could not update the room.')
    }
  }

  const handleInvite = async (room) => {
    const email = window.prompt(`Invite a user to "${room.titre}". Enter their email:`)
    if (!email) return
    try {
      await api.post('/Invitation/add', {
        salonTitre: room.titre,
        invitedEmail: email.trim(),
      })
      setInfo(`Invitation sent to ${email.trim()}.`)
    } catch (err) {
      console.error(err)
      setError('Could not send the invitation.')
    }
  }

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  if (!isAuthenticated) {
    return null
  }

  return (
    <div style={{ padding: '20px', maxWidth: '1100px', margin: '0 auto' }}>
      <nav
        style={{
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          marginBottom: '24px',
          gap: '12px',
          flexWrap: 'wrap',
        }}
      >
        <div>
          <h1 style={{ margin: 0 }}>ChatRoom Dashboard</h1>
          <p style={{ margin: '4px 0 0', color: '#555' }}>
            Signed in as {user?.pseudo || user?.email}
          </p>
        </div>
        <div style={{ display: 'flex', gap: '8px' }}>
          <Link to="/" style={{ padding: '8px 12px' }}>
            Home
          </Link>
          <button type="button" onClick={() => navigate('/profile')}>
            Profile
          </button>
          <button type="button" onClick={handleLogout}>
            Log out
          </button>
        </div>
      </nav>

      {error && <p style={{ color: '#b00020' }}>{error}</p>}
      {info && <p style={{ color: '#0a7a32' }}>{info}</p>}

      <form
        onSubmit={handleCreateRoom}
        style={{
          marginBottom: '24px',
          border: '1px solid #ccc',
          padding: '16px',
          borderRadius: '8px',
          display: 'grid',
          gap: '8px',
          maxWidth: '480px',
        }}
      >
        <h3 style={{ margin: 0 }}>Create a room</h3>
        <input
          type="text"
          placeholder="Room title"
          value={newRoomName}
          onChange={(e) => setNewRoomName(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Description (optional)"
          value={newRoomDescription}
          onChange={(e) => setNewRoomDescription(e.target.value)}
        />
        <button type="submit">Create</button>
      </form>

      {loading ? (
        <p>Loading rooms…</p>
      ) : (
        <div style={{ display: 'flex', gap: '20px', alignItems: 'flex-start', flexWrap: 'wrap' }}>
          <section
            style={{
              flex: 1,
              minWidth: '280px',
              border: '1px solid #ddd',
              padding: '16px',
              borderRadius: '8px',
            }}
          >
            <h3>My rooms (created)</h3>
            <ul style={{ listStyle: 'none', padding: 0, margin: 0 }}>
              {myPage.pageItems.map((room) => (
                <li
                  key={room.titre}
                  style={{
                    marginBottom: '12px',
                    borderBottom: '1px solid #eee',
                    paddingBottom: '8px',
                  }}
                >
                  <strong>{room.titre}</strong>
                  <div style={{ color: '#555' }}>{room.description}</div>
                  <div style={{ marginTop: '6px', display: 'flex', gap: '6px', flexWrap: 'wrap' }}>
                    <button type="button" onClick={() => openRoom(room.titre)}>
                      Open
                    </button>
                    <button type="button" onClick={() => handleEdit(room)}>
                      Edit
                    </button>
                    <button type="button" onClick={() => handleInvite(room)}>
                      Invite
                    </button>
                  </div>
                </li>
              ))}
              {myRooms.length === 0 && <p>No rooms created yet.</p>}
            </ul>
            {myPage.totalPages > 1 && (
              <div style={{ display: 'flex', gap: '8px', alignItems: 'center', marginTop: '12px' }}>
                <button
                  type="button"
                  disabled={myPage.page === 1}
                  onClick={() => setPageMyRooms((p) => p - 1)}
                >
                  Prev
                </button>
                <span>
                  Page {myPage.page} / {myPage.totalPages}
                </span>
                <button
                  type="button"
                  disabled={myPage.page === myPage.totalPages}
                  onClick={() => setPageMyRooms((p) => p + 1)}
                >
                  Next
                </button>
              </div>
            )}
          </section>

          <section
            style={{
              flex: 1,
              minWidth: '280px',
              border: '1px solid #ddd',
              padding: '16px',
              borderRadius: '8px',
              background: '#fafafa',
            }}
          >
            <h3>Rooms I belong to</h3>
            <ul style={{ listStyle: 'none', padding: 0, margin: 0 }}>
              {memberPage.pageItems.map((room) => (
                <li
                  key={`member-${room.titre}`}
                  style={{
                    marginBottom: '12px',
                    borderBottom: '1px solid #eee',
                    paddingBottom: '8px',
                  }}
                >
                  <strong>{room.titre}</strong>
                  <div style={{ color: '#555' }}>
                    {room.description}
                    {room.creatorPseudo ? ` · by ${room.creatorPseudo}` : ''}
                  </div>
                  <div style={{ marginTop: '6px' }}>
                    <button type="button" onClick={() => openRoom(room.titre)}>
                      Join
                    </button>
                  </div>
                </li>
              ))}
              {memberRooms.length === 0 && <p>No member rooms yet.</p>}
            </ul>
            {memberPage.totalPages > 1 && (
              <div style={{ display: 'flex', gap: '8px', alignItems: 'center', marginTop: '12px' }}>
                <button
                  type="button"
                  disabled={memberPage.page === 1}
                  onClick={() => setPageMemberRooms((p) => p - 1)}
                >
                  Prev
                </button>
                <span>
                  Page {memberPage.page} / {memberPage.totalPages}
                </span>
                <button
                  type="button"
                  disabled={memberPage.page === memberPage.totalPages}
                  onClick={() => setPageMemberRooms((p) => p + 1)}
                >
                  Next
                </button>
              </div>
            )}
          </section>
        </div>
      )}
    </div>
  )
}
