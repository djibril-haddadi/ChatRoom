package com.example.demo;

import java.util.Date;
import jakarta.persistence.*;


@Entity
@Table(name = "evenement")
public class Evenement {
    @Id
    @GeneratedValue
    protected long id;
    protected Date date;

    @ManyToOne
    @JoinColumn(name = "salon_titre")
    protected Salon salon;

    public Evenement(){}


    public Evenement(Date newDate){
        this.date = newDate;
    }

    //Getter et setter

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Salon getSalon() {
        return salon;
    }
    public void setSalon(Salon salon) {
        this.salon = salon;
    }
}
