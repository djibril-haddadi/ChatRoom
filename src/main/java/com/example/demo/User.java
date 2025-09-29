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
    private List<Message> messages;
    private List<Salon> salons;
    private List<Salon> salonsCree;
    private List<Invitation> invitations;

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
}