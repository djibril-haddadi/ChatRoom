package DTO;

import com.example.demo.Salon;
import com.example.demo.User;

import java.util.Date;

public class MessageDTO {

    private Long id;
    private String contenu;
    private Date date;
    private Salon salon;
    private User sender;

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
