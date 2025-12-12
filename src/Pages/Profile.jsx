import React, { useState } from 'react';

const Profile = () => {
    // Fausses données utilisateur pour l'instant
    const [user, setUser] = useState({
        username: "MonPseudo",
        email: "etudiant@utc.fr",
        isActive: true
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUser({ ...user, [name]: value });
    };

    const toggleAccount = () => {
        setUser({ ...user, isActive: !user.isActive });
    };

    return (
        <div style={{ padding: "20px", maxWidth: "600px", margin: "0 auto" }}>
            <h2>👤 Mon Profil</h2>

            <div style={{ border: "1px solid #ddd", padding: "20px", borderRadius: "8px" }}>
                {/* Champ Pseudo */}
                <div style={{ marginBottom: "15px" }}>
                    <label>Pseudo :</label>
                    <input
                        type="text"
                        name="username"
                        value={user.username}
                        onChange={handleChange}
                        style={{ display: "block", width: "100%", padding: "8px", marginTop: "5px" }}
                    />
                </div>

                {/* Champ Email */}
                <div style={{ marginBottom: "15px" }}>
                    <label>Email :</label>
                    <input
                        type="email"
                        name="email"
                        value={user.email}
                        onChange={handleChange}
                        style={{ display: "block", width: "100%", padding: "8px", marginTop: "5px" }}
                    />
                </div>

                {/* Gestion du compte */}
                <div style={{ marginTop: "20px", paddingTop: "20px", borderTop: "1px solid #eee" }}>
                    <label>État du compte : </label>
                    <span style={{ fontWeight: "bold", color: user.isActive ? "green" : "red" }}>
                {user.isActive ? "ACTIF" : "DÉSACTIVÉ"}
            </span>
                    <br /><br />

                    <button
                        onClick={toggleAccount}
                        style={{
                            backgroundColor: user.isActive ? "#dc3545" : "#28a745",
                            color: "white", padding: "10px", border: "none", borderRadius: "5px", cursor: "pointer"
                        }}
                    >
                        {user.isActive ? "Désactiver mon compte" : "Réactiver mon compte"}
                    </button>

                    <button
                        onClick={() => alert("✅ Modifications sauvegardées pour " + user.username + " !")}
                        style={{
                            marginLeft: "10px",
                            padding: "10px",
                            backgroundColor: "#007bff",
                            color: "white",
                            border: "none",
                            borderRadius: "5px",
                            cursor: "pointer"  // Ajoute la petite main au survol
                        }}
                    >
                        Sauvegarder les modifications
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Profile;