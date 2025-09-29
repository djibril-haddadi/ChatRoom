package com.example.demo;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "salon")
public class Salon {
    @Id
    private String titre;
    private String description;
    private List<Evenement> evenements;// on pourrait utiliser une autre structure
    private List<Message> messages;
    private List<User> userList;
}
