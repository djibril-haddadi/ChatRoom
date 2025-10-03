package com.example.demo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Suppression extends Evenement{
    private String raison;
    @OneToOne
    private User userSupprime;

    public Suppression(){}

    Suppression(User newUserSupprime, String newRaison, Date newDate){
        super(newDate);
        this.userSupprime = newUserSupprime;
        this.raison = newRaison;
    }

}
