package com.example.demo.DTO;

import com.example.demo.*;
import jakarta.persistence.*;

import java.util.List;

public class UserDTO {

    private String email;
    private String nom;
    private String prenom;
    private String pseudo;
    private String mdp;
    private List<Message> messages;
    private List<Salon> salons;
    private List<Salon> salonsCree;
    private List<Invitation> invitations;
    private List<Suppression> suppressions;

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
