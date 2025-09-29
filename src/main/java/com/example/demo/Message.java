package com.example.demo;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private String contenu;
    private Date date;

}
