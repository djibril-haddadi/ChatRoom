package com.example.demo;

import jakarta.persistence.*;

enum Etat {
    EN_ATTENTE,
    ACCEPTEE,
    REFUSEE,
    ANNULE
}

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Invitation extends Evenement{
    private Etat etat;
    private User userInvite;
}
