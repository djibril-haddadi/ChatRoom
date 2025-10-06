package DTO;

import com.example.demo.Evenement;
import com.example.demo.Message;
import com.example.demo.User;
import jakarta.persistence.*;

import java.util.List;

public class SalonDTO {

    private String titre;
    private String description;
    private List<Evenement> evenements;// on pourrait utiliser une autre structure
    private List<Message> messages;
    private List<User> userList;

    private User creator;

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
