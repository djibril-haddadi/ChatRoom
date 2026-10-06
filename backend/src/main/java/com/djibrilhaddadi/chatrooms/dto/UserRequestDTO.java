package com.djibrilhaddadi.chatrooms.dto;

import com.djibrilhaddadi.chatrooms.entity.*;

import java.util.List;

public class UserRequestDTO {

    private String email;
    private String nom;
    private String prenom;
    private String pseudo;
    private String mdp;

    public UserRequestDTO(){}

    public UserRequestDTO(String email, String nom, String prenom, String pseudo, String mdp){
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mdp = mdp;
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

}
