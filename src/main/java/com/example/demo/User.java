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

    //Getter et setter

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getPrenom() {
        return prenom;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public String getPseudo() {
        return pseudo;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public String getMdp() {
        return mdp;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setSalons(List<Salon> salons) {
        this.salons = salons;
    }
    public List<Salon> getSalons() {
        return salons;
    }

    public void setSalonsCree(List<Salon> salonsCree) {
        this.salonsCree = salonsCree;
    }
    public List<Salon> getSalonsCree() {
        return salonsCree;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }
    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setSuppressions(List<Suppression> suppressions) {
        this.suppressions = suppressions;
    }
    public List<Suppression> getSuppressions() {
        return suppressions;
    }

}