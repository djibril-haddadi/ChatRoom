package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "eventSupressions")
public class Supression extends Evenement{
    private String raison;
    private User userSuprime;
}
