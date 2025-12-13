package com.example.demo.DTO;

import com.example.demo.Invitation;
import com.example.demo.Message;
import com.example.demo.Salon;
import com.example.demo.Suppression;

import java.util.List;

public class UserResponseDTO {

    private String email;
    private String nom;
    private String prenom;
    private String pseudo;
    private boolean active;
    private String salonActif;

    public UserResponseDTO(){}

    public UserResponseDTO(String email, String nom, String prenom, String pseudo, String mdp){
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
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

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setSalonActif(String salonActif) {
        this.salonActif = salonActif;
    }

    public String getSalonActif() {
        return salonActif;
    }
}
