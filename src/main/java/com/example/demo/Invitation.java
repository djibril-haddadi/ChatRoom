package com.example.demo;

import jakarta.persistence.*;

import java.util.Date;

enum Etat {
    EN_ATTENTE,
    ACCEPTEE,
    REFUSEE,
    ANNULE
}

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Invitation extends Evenement{
    @Enumerated(EnumType.STRING)
    private Etat etat;
    @OneToOne
    private User userInvite;

    @ManyToOne
    @JoinColumn(name = "invited_email")
    private User invited;


    public Invitation(){}

    Invitation(Etat newEtat, User newUserInvite, Date newDate){
        super(newDate);
        this.etat = newEtat;
        this.userInvite = newUserInvite;
    }
}
