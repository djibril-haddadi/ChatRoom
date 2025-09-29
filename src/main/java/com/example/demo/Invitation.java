package com.example.demo;

import jakarta.persistence.*;

enum Etat {
    EN_ATTENTE,
    ACCEPTEE,
    REFUSEE,
    ANNULE
}

@Entity
@Table(name = "eventInvitation")
public class Invitation extends Evenement{
    private Etat etat;
    private User userInvite;
}
