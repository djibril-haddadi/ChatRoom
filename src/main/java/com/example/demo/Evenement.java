package com.example.demo;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "evenements")
public class Evenement {
    @Id
    @GeneratedValue
    private int id;
    private Date date;
}
