import React, { useState } from 'react';
import { useParams } from 'react-router-dom';

import api from '../Components/utils/api.jsx';

const ChatRoom = () => {
    const { id } = useParams();
    const [messageInput, setMessageInput] = useState("");

    // 1. Liste des utilisateurs connectés (Ajout pour respecter la consigne)
    // Dans un vrai projet, cela viendrait du Backend/WebSocket
    const [connectedUsers] = useState([
        { id: 1, name: "Lucas", status: "En ligne" },
        { id: 2, name: "Moi", status: "En ligne" },
        { id: 3, name: "Prof", status: "Absent" },
        { id: 4, name: "Emma", status: "En ligne" },
    ]);

    const [messages, setMessages] = useState([
        { sender: "Lucas", content: "Salut ! Tu as avancé sur le React ?", isMe: false },
        { sender: "Moi", content: "Oui, j'ai fini le Dashboard !", isMe: true },
        { sender: "Prof", content: "Très bien, continuez comme ça.", isMe: false }
    ]);

    const sendMessage = (e) => {
        e.preventDefault();
        if (messageInput.trim()) {
            setMessages([...messages, { sender: "Moi", content: messageInput, isMe: true }]);
            setMessageInput("");
        }
    };

    return (
        // CONTENEUR PRINCIPAL : Flex Row pour séparer Chat et Utilisateurs
        <div style={{
            maxWidth: "1000px",
            margin: "20px auto",
            border: "1px solid #ccc",
            borderRadius: "8px",
            height: "80vh",
            display: "flex",
            overflow: "hidden" // Empêche le débordement global
        }}>

            {/* --- PARTIE GAUCHE : LE CHAT (Flex 3) --- */}
            <div style={{ flex: 3, display: "flex", flexDirection: "column", borderRight: "1px solid #ccc" }}>

                {/* Titre du salon */}
                <div style={{ padding: "15px", backgroundColor: "#007bff", color: "white" }}>
                    <h3 style={{ margin: 0 }}>💬 Salon N°{id}</h3>
                </div>

                {/* Zone des messages */}
                <div style={{ flex: 1, padding: "20px", overflowY: "auto", backgroundColor: "#f9f9f9" }}>
                    {messages.map((msg, index) => (
                        <div key={index} style={{
                            display: "flex",
                            justifyContent: msg.isMe ? "flex-end" : "flex-start",
                            marginBottom: "10px"
                        }}>
                            <div style={{
                                maxWidth: "70%",
                                padding: "10px 15px",
                                borderRadius: "15px",
                                backgroundColor: msg.isMe ? "#007bff" : "white",
                                color: msg.isMe ? "white" : "black",
                                boxShadow: "0 1px 2px rgba(0,0,0,0.1)"
                            }}>
                                {!msg.isMe && <small style={{ fontWeight: "bold", display: "block", marginBottom: "5px" }}>{msg.sender}</small>}
                                {msg.content}
                            </div>
                        </div>
                    ))}
                </div>

                {/* Input Message */}
                <form onSubmit={sendMessage} style={{ padding: "15px", borderTop: "1px solid #ddd", display: "flex", gap: "10px", backgroundColor: "white" }}>
                    <input
                        type="text"
                        placeholder="Écris un message..."
                        value={messageInput}
                        onChange={(e) => setMessageInput(e.target.value)}
                        style={{ flex: 1, padding: "10px", borderRadius: "20px", border: "1px solid #ccc" }}
                    />
                    <button type="submit" style={{ padding: "10px 20px", borderRadius: "20px", border: "none", backgroundColor: "#28a745", color: "white", cursor: "pointer" }}>
                        Envoyer
                    </button>
                </form>
            </div>

            {/* --- PARTIE DROITE : UTILISATEURS CONNECTÉS (Flex 1) --- */}
            <div style={{ flex: 1, backgroundColor: "#fff", display: "flex", flexDirection: "column" }}>
                <div style={{ padding: "15px", borderBottom: "1px solid #ddd", backgroundColor: "#f8f9fa" }}>
                    <h4 style={{ margin: 0 }}>👥 Membres ({connectedUsers.length})</h4>
                </div>

                <ul style={{ listStyle: "none", padding: "0", margin: "0", overflowY: "auto" }}>
                    {connectedUsers.map((user) => (
                        <li key={user.id} style={{ padding: "12px 15px", borderBottom: "1px solid #eee", display: "flex", alignItems: "center", gap: "10px" }}>
                            {/* Petite pastille de couleur pour le statut */}
                            <span style={{
                                width: "10px",
                                height: "10px",
                                borderRadius: "50%",
                                backgroundColor: user.status === "En ligne" ? "#28a745" : "#ffc107"
                            }}></span>
                            <span>{user.name}</span>
                        </li>
                    ))}
                </ul>
            </div>

        </div>
    );
};

export default ChatRoom;