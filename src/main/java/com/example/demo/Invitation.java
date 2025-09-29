package com.example.demo;

enum Etat {
    EN_ATTENTE,
    ACCEPTEE,
    REFUSEE,
    ANNULE
}
public class Invitation extends Evenement{
    private Etat etat;
}
