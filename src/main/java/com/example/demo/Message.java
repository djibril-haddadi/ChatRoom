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

}
