package com.example.demo;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "salon")
public class Salon {
    @Id
    private String titre;
    private String description;

    @OneToMany(mappedBy = "salon", cascade = CascadeType.ALL)
    private List<Evenement> evenements;// on pourrait utiliser une autre structure

    @OneToMany(mappedBy = "salon", cascade = CascadeType.ALL)
    private List<Message> messages;

    @ManyToMany(mappedBy = "salons", cascade = CascadeType.ALL)
    private List<User> userList;

    @ManyToOne
    @JoinColumn(name = "creator_email")
    private User creator;

    Salon(){}
    Salon(String newTitre){
        this.titre = newTitre;
    }
}
