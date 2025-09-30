package com.example.demo;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Supression extends Evenement{
    private String raison;
    @OneToOne
    private User userSuprime;
}
