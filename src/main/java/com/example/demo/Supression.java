package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "eventSupression")
public class Supression extends Evenement{
    private String raison;
    private User userSuprime;
}
