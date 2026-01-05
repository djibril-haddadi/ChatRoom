package com.example.demo.DTO;

import java.util.Date;

public class EvenementRequestDTO {
    private long id;
    private String salonTitre;

    public String getSalonTitre() {
        return salonTitre;
    }

    public void setSalonTitre(String salonTitre) {
        this.salonTitre = salonTitre;
    }

    public long getId() {
        return id;
    }
}
