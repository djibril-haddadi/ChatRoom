package com.example.demo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class User {
    @Id
    private String email;
    private String nom;
    private String prenom;
    private String pseudo;
    private String mdp;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> messages;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "User_Salon",
            joinColumns = @JoinColumn(name="utilisateur_email"),
            inverseJoinColumns = @JoinColumn(name = "Salon_titre")
    )
    private List<Salon> salons;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Salon> salonsCree;

    @OneToMany(mappedBy = "invited", cascade = CascadeType.ALL)
    private List<Invitation> invitations;

    @OneToMany(mappedBy = "userSupprime", cascade = CascadeType.ALL )
    private List<Suppression> suppressions;


    User(String newEmail, String newNom, String newPrenom, String newPseudo, String newMdp){
        this.email = newEmail;
        this.nom = newNom;
        this.prenom = newPrenom;
        this.pseudo = newPseudo;
        this.mdp = newMdp;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
}