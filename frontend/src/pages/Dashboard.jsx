import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Dashboard = () => {
    const navigate = useNavigate();

    // --- CONFIGURATION PAGINATION ---
    const ITEMS_PER_PAGE = 3; // Change ce chiffre si tu veux plus ou moins d'éléments par page

    // --- STATES ---
    const [newRoomName, setNewRoomName] = useState("");

    // 1. Ajout des states pour gérer le numéro de page actuel de chaque liste
    const [pageMyRooms, setPageMyRooms] = useState(1);
    const [pageInvitedRooms, setPageInvitedRooms] = useState(1);

    const [myRooms, setMyRooms] = useState([
        { id: 1, name: "Projet Java", description: "Discussion sur le backend" },
        { id: 2, name: "Pause Café", description: "Détente" },
        { id: 3, name: "Aide React", description: "Questions frontend" },
        { id: 4, name: "Groupe de révision", description: "Pour les partiels" } // J'ai ajouté des exemples pour tester la pagination
    ]);

    const [invitedRooms, setInvitedRooms] = useState([
        { id: 10, name: "Général", owner: "Lucas" }
    ]);

    // --- LOGIQUE DE CALCUL (PAGINATION) ---

    // Calcul pour "Mes Salons"
    const idxLastMyRoom = pageMyRooms * ITEMS_PER_PAGE;
    const idxFirstMyRoom = idxLastMyRoom - ITEMS_PER_PAGE;
    const currentMyRooms = myRooms.slice(idxFirstMyRoom, idxLastMyRoom);
    const totalPagesMyRooms = Math.ceil(myRooms.length / ITEMS_PER_PAGE);

    // Calcul pour "Salons Invités"
    const idxLastInvited = pageInvitedRooms * ITEMS_PER_PAGE;
    const idxFirstInvited = idxLastInvited - ITEMS_PER_PAGE;
    const currentInvitedRooms = invitedRooms.slice(idxFirstInvited, idxLastInvited);
    const totalPagesInvited = Math.ceil(invitedRooms.length / ITEMS_PER_PAGE);

    // --- ACTIONS LOGIQUES ---

    const handleCreateRoom = () => {
        if (newRoomName.trim() === "") return;
        const newRoom = {
            id: Date.now(),
            name: newRoomName,
            description: "Nouveau salon créé"
        };
        setMyRooms([...myRooms, newRoom]);
        setNewRoomName("");
    };

    const handleQuit = (id) => {
        if (window.confirm("Voulez-vous vraiment quitter ce salon ?")) {
            const updatedList = invitedRooms.filter(room => room.id !== id);
            setInvitedRooms(updatedList);

            // Si on vide une page, on revient à la précédente
            if (currentInvitedRooms.length === 1 && pageInvitedRooms > 1) {
                setPageInvitedRooms(pageInvitedRooms - 1);
            }
        }
    };

    const handleEdit = (id) => {
        const roomToEdit = myRooms.find(r => r.id === id);
        const newName = prompt("Nouveau nom du salon :", roomToEdit.name);
        if (newName) {
            const updatedList = myRooms.map(room => {
                if (room.id === id) return { ...room, name: newName };
                return room;
            });
            setMyRooms(updatedList);
        }
    };

    const handleInvite = (roomName) => {
        const email = prompt(`Inviter un utilisateur dans "${roomName}". Saisissez son email :`);
        if (email) alert(`Invitation envoyée à ${email} !`);
    };

    return (
        <div style={{ padding: "20px" }}>
            {/* En-tête */}
            <nav style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: "30px" }}>
                <h1>🏠 Dashboard Salons</h1>
                <button onClick={() => navigate('/profile')} style={{ padding: "10px", backgroundColor: "#007bff", color: "white", border: "none", borderRadius: "5px", cursor: "pointer" }}>
                    👤 Mon Profil
                </button>
            </nav>

            {/* Planifier un salon */}
            <div style={{ marginBottom: "30px", border: "1px solid #ccc", padding: "15px", borderRadius: "8px" }}>
                <h3>➕ Planifier un nouveau salon</h3>
                <input
                    type="text"
                    placeholder="Nom du salon..."
                    value={newRoomName}
                    onChange={(e) => setNewRoomName(e.target.value)}
                    style={{ marginRight: "10px", padding: "5px" }}
                />
                <button onClick={handleCreateRoom} style={{ padding: "5px 15px", cursor: "pointer" }}>Créer</button>
            </div>

            <div style={{ display: "flex", gap: "20px", alignItems: "flex-start" }}>

                {/* --- SECTION 1 : MES SALONS (PAGINÉE) --- */}
                <div style={{ flex: 1, border: "1px solid #ddd", padding: "15px", borderRadius: "8px", minHeight: "350px", display: "flex", flexDirection: "column" }}>
                    <h3>📂 Mes Salons</h3>

                    <ul style={{ flex: 1 }}>
                        {/* On map sur 'currentMyRooms' (la liste coupée) et non plus 'myRooms' (la liste complète) */}
                        {currentMyRooms.map(room => (
                            <li key={room.id} style={{ marginBottom: "10px", listStyle: "none", borderBottom: "1px solid #eee", paddingBottom: "5px" }}>
                                <strong>{room.name}</strong> - {room.description} <br />
                                <div style={{ marginTop: "5px" }}>
                                    <button onClick={() => navigate(`/chat/${room.id}`)} style={{ marginRight: "5px", cursor: "pointer" }}>Ouvrir</button>
                                    <button onClick={() => handleEdit(room.id)} style={{ marginRight: "5px", backgroundColor: "#ffc107", border: "none", cursor: "pointer", padding: "2px 8px" }}>Modifier</button>
                                    <button onClick={() => handleInvite(room.name)} style={{ backgroundColor: "#28a745", color: "white", border: "none", cursor: "pointer", padding: "2px 8px" }}>Inviter</button>
                                </div>
                            </li>
                        ))}
                        {myRooms.length === 0 && <p>Aucun salon créé.</p>}
                    </ul>

                    {/* Contrôles Pagination Mes Salons */}
                    {totalPagesMyRooms > 1 && (
                        <div style={{ display: "flex", justifyContent: "center", gap: "10px", marginTop: "10px", borderTop: "1px solid #eee", paddingTop: "10px" }}>
                            <button disabled={pageMyRooms === 1} onClick={() => setPageMyRooms(p => p - 1)}>⬅️</button>
                            <span>Page {pageMyRooms} / {totalPagesMyRooms}</span>
                            <button disabled={pageMyRooms === totalPagesMyRooms} onClick={() => setPageMyRooms(p => p + 1)}>➡️</button>
                        </div>
                    )}
                </div>

                {/* --- SECTION 2 : SALONS INVITÉS (PAGINÉE) --- */}
                <div style={{ flex: 1, border: "1px solid #ddd", padding: "15px", borderRadius: "8px", backgroundColor: "#f9f9f9", minHeight: "350px", display: "flex", flexDirection: "column" }}>
                    <h3>📩 Salons Invités</h3>

                    <ul style={{ flex: 1 }}>
                        {/* On map sur 'currentInvitedRooms' */}
                        {currentInvitedRooms.map(room => (
                            <li key={room.id} style={{ marginBottom: "10px", listStyle: "none", borderBottom: "1px solid #ddd", paddingBottom: "5px" }}>
                                <strong>{room.name}</strong> (Par {room.owner}) <br />
                                <div style={{ marginTop: "5px" }}>
                                    <button onClick={() => navigate(`/chat/${room.id}`)} style={{ marginRight: "5px", cursor: "pointer" }}>Rejoindre</button>
                                    <button onClick={() => handleQuit(room.id)} style={{ backgroundColor: "#dc3545", color: "white", border: "none", cursor: "pointer", padding: "2px 8px" }}>Quitter</button>
                                </div>
                            </li>
                        ))}
                        {invitedRooms.length === 0 && <p>Aucune invitation.</p>}
                    </ul>

                    {/* Contrôles Pagination Salons Invités */}
                    {totalPagesInvited > 1 && (
                        <div style={{ display: "flex", justifyContent: "center", gap: "10px", marginTop: "10px", borderTop: "1px solid #ddd", paddingTop: "10px" }}>
                            <button disabled={pageInvitedRooms === 1} onClick={() => setPageInvitedRooms(p => p - 1)}>⬅️</button>
                            <span>Page {pageInvitedRooms} / {totalPagesInvited}</span>
                            <button disabled={pageInvitedRooms === totalPagesInvited} onClick={() => setPageInvitedRooms(p => p + 1)}>➡️</button>
                        </div>
                    )}
                </div>

            </div>
        </div>
    );
};

export default Dashboard;