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
    @OneToMany
    private List<Message> messages;
    @OneToMany
    private List<Salon> salons;
    @OneToMany
    private List<Salon> salonsCree;
    @OneToMany
    private List<Invitation> invitations;

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