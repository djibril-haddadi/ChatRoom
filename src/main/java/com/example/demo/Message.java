package com.example.demo;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private String contenu;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "salon_titre")
    private Salon salon;

    @ManyToOne
    @JoinColumn(name = "sender_email")
    private User sender;

    Message(String newContenu, Date newDate){
        this.contenu = newContenu;
        this.date = newDate;
    }

    //Getter et setter

    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Salon getSalon() {
        return salon;
    }
    public void setSalon(Salon salon) {
        this.salon = salon;
    }

}
