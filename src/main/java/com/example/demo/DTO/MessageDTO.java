package com.example.demo.DTO;

import com.example.demo.Message;
import com.example.demo.Salon;
import com.example.demo.User;

import java.util.Date;

public class MessageDTO {

    private Long id;
    private String contenu;
    private Date date;
    private Salon salon;
    private User sender;

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.contenu = message.getContenu();
        this.date = message.getDate();
        this.salon = message.getSalon();
        this.sender = message.getSender();
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

    public Long getId() {
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
    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
}
