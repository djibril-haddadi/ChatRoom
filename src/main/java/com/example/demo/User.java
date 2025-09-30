package com.example.demo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class User {
    private String nom;
    private String prenom;
    private String pseudo;
    @Id
    private String email;
    private String mdp;
    @OneToMany
    private List<Message> messages;
    @OneToMany
    private List<Salon> salons;
    @OneToMany
    private List<Salon> salonsCree;
    @OneToMany
    private List<Invitation> invitations;

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
}