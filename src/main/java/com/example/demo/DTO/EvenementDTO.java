package com.example.demo.DTO;

import com.example.demo.Salon;

import java.util.Date;

public class EvenementDTO {

    protected int id;
    protected Date date;
    protected Salon salon;

    //Getter et setter

    public int getId() {
        return id;
    }
    public void setId(int id) {
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
