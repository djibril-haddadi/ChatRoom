package com.example.demo;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "salon")
public class Salon {
    @Id
    private String titre;
    private String description;
    @OneToMany
    private List<Evenement> evenements;// on pourrait utiliser une autre structure
    @OneToMany
    private List<Message> messages;
    @OneToMany
    private List<User> userList;

    Salon(String newTitre){
        this.titre = newTitre;
    }
}
