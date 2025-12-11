package com.example.demo.DTO;

import java.util.Date;

public class EvenementResponseDTO {
    private Date date;
    private String salonTitre;

    public String getSalonTitre() {
        return salonTitre;
    }

    public void setSalonTitre(String salonTitre) {
        this.salonTitre = salonTitre;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
