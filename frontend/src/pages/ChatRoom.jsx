import { useCallback, useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import { useAuth } from '../components/auth/AuthContext'
import api from '../services/api'

export default function ChatRoom() {
  const { id } = useParams()
  const titre = decodeURIComponent(id || '')
  const navigate = useNavigate()
  const { user, isAuthenticated } = useAuth()

  const [messageInput, setMessageInput] = useState('')
  const [messages, setMessages] = useState([])
  const [members, setMembers] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [sending, setSending] = useState(false)

  const loadRoomData = useCallback(async () => {
    if (!titre) return
    setLoading(true)
    setError('')
    try {
      const [messagesRes, membersRes] = await Promise.all([
        api.get(`/Salon/${encodeURIComponent(titre)}/messages`),
        api.get(`/Salon/${encodeURIComponent(titre)}/user`),
      ])
      setMessages(messagesRes.data || [])
      setMembers(membersRes.data || [])
    } catch (err) {
      console.error(err)
      setError('Unable to load room data from the API.')
    } finally {
      setLoading(false)
    }
  }, [titre])

  useEffect(() => {
    if (!isAuthenticated) {
      navigate('/login')
      return
    }
    loadRoomData()
  }, [isAuthenticated, navigate, loadRoomData])

  const sendMessage = async (e) => {
    e.preventDefault()
    if (!messageInput.trim() || !user?.email) return
    setSending(true)
    setError('')
    try {
      await api.post('/Message/add', {
        contenu: messageInput.trim(),
        salonTitre: titre,
        senderEmail: user.email,
      })
      setMessageInput('')
      await loadRoomData()
    } catch (err) {
      console.error(err)
      setError('Could not send the message.')
    } finally {
      setSending(false)
    }
  }

  if (!isAuthenticated) {
    return null
  }

  return (
    <div
      style={{
        maxWidth: '1000px',
        margin: '20px auto',
        border: '1px solid #ccc',
        borderRadius: '8px',
        height: '80vh',
        display: 'flex',
        flexDirection: 'column',
        overflow: 'hidden',
      }}
    >
      <div
        style={{
          padding: '12px 16px',
          borderBottom: '1px solid #ddd',
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          gap: '12px',
        }}
      >
        <div>
          <h2 style={{ margin: 0 }}>{titre || 'Room'}</h2>
          <small style={{ color: '#666' }}>ChatRooms</small>
        </div>
        <div style={{ display: 'flex', gap: '8px' }}>
          <Link to="/dashboard">Dashboard</Link>
          <button type="button" onClick={() => loadRoomData()}>
            Refresh
          </button>
        </div>
      </div>

      {error && (
        <p style={{ color: '#b00020', margin: '8px 16px' }}>{error}</p>
      )}

      <div style={{ flex: 1, display: 'flex', minHeight: 0 }}>
        <div style={{ flex: 3, display: 'flex', flexDirection: 'column', borderRight: '1px solid #ccc' }}>
          <div style={{ flex: 1, padding: '16px', overflowY: 'auto', backgroundColor: '#f9f9f9' }}>
            {loading && <p>Loading messages…</p>}
            {!loading && messages.length === 0 && <p>No messages yet. Say hello!</p>}
            {messages.map((msg, index) => {
              const isMe = msg.senderEmail === user?.email
              return (
                <div
                  key={`${msg.date || index}-${msg.senderEmail}-${index}`}
                  style={{
                    display: 'flex',
                    justifyContent: isMe ? 'flex-end' : 'flex-start',
                    marginBottom: '10px',
                  }}
                >
                  <div
                    style={{
                      maxWidth: '70%',
                      padding: '10px 15px',
                      borderRadius: '15px',
                      backgroundColor: isMe ? '#007bff' : 'white',
                      color: isMe ? 'white' : 'black',
                      boxShadow: '0 1px 2px rgba(0,0,0,0.1)',
                    }}
                  >
                    {!isMe && (
                      <small style={{ fontWeight: 'bold', display: 'block', marginBottom: '5px' }}>
                        {msg.senderEmail}
                      </small>
                    )}
                    {msg.contenu}
                  </div>
                </div>
              )
            })}
          </div>

          <form
            onSubmit={sendMessage}
            style={{
              padding: '12px',
              borderTop: '1px solid #ddd',
              display: 'flex',
              gap: '10px',
              backgroundColor: 'white',
            }}
          >
            <input
              type="text"
              placeholder="Write a message…"
              value={messageInput}
              onChange={(e) => setMessageInput(e.target.value)}
              style={{ flex: 1, padding: '10px', borderRadius: '20px', border: '1px solid #ccc' }}
              disabled={sending}
            />
            <button type="submit" disabled={sending || !messageInput.trim()}>
              {sending ? 'Sending…' : 'Send'}
            </button>
          </form>
        </div>

        <aside style={{ flex: 1, backgroundColor: '#fff', display: 'flex', flexDirection: 'column' }}>
          <div style={{ padding: '15px', borderBottom: '1px solid #ddd', backgroundColor: '#f8f9fa' }}>
            <h4 style={{ margin: 0 }}>Members ({members.length})</h4>
          </div>
          <ul style={{ listStyle: 'none', padding: 0, margin: 0, overflowY: 'auto' }}>
            {members.map((member) => (
              <li
                key={member.email}
                style={{
                  padding: '12px 15px',
                  borderBottom: '1px solid #eee',
                  display: 'flex',
                  alignItems: 'center',
                  gap: '10px',
                }}
              >
                <span
                  style={{
                    width: '10px',
                    height: '10px',
                    borderRadius: '50%',
                    backgroundColor: member.active ? '#28a745' : '#adb5bd',
                  }}
                />
                <span>{member.pseudo || member.email}</span>
              </li>
            ))}
            {!loading && members.length === 0 && (
              <li style={{ padding: '12px 15px', color: '#666' }}>No members listed.</li>
            )}
          </ul>
        </aside>
      </div>
    </div>
  )
}
