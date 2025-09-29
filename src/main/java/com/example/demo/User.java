package com.example.demo;

import java.util.List;

public class User {
    private String nom;
    private String prenom;
    private String pseudo;
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