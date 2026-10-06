package com.djibrilhaddadi.chatrooms.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Suppression extends Evenement{
    private String raison;

    @ManyToOne
    @JoinColumn(name = "userSupprime_email")
    private User userSupprime;

    public Suppression(){}



    public Suppression(User newUserSupprime, String newRaison, Date newDate){
        super(newDate);
        this.userSupprime = newUserSupprime;
        this.raison = newRaison;
    }

    //Getter et setter

    public String getRaison() {
        return raison;
    }
    public void setRaison(String raison) {
        this.raison = raison;
    }

    public User getUserSupprime() {
        return userSupprime;
    }
    public void setUserSupprime(User userSupprime) {
        this.userSupprime = userSupprime;
    }

}
