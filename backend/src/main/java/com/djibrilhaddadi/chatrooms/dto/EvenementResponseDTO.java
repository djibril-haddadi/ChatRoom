package com.djibrilhaddadi.chatrooms.dto;

import java.util.Date;

public class EvenementResponseDTO {
    private long id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
