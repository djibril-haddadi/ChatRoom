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

    public Salon(){}

    public Salon(String newTitre, User creator){
        this.titre = newTitre;
        this.creator = creator;
    }

    //Getter et setter

    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Evenement> getEvenements() {
        return evenements;
    }
    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }

    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getCreator() {
        return creator;
    }
    public void setCreator(User creator) {
        this.creator = creator;
    }

}
