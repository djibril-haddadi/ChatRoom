package com.example.demo;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "evenement")
public class Evenement {
    @Id
    @GeneratedValue
    protected int id;
    protected Date date;

    Evenement(Date newDate){
        this.date = newDate;
    }
}
