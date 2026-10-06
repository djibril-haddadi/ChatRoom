package com.djibrilhaddadi.chatrooms.dto;

import java.util.Date;

public class MessageResponseDTO {

    private String contenu;
    private Date date;
    private String senderEmail;

    public MessageResponseDTO(){}
    public MessageResponseDTO(String contenu, Date date, String senderEmail) {
        this.contenu = contenu;
        this.date = date;
        this.senderEmail = senderEmail;
    }

    //Getter et setter

    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    public String getSenderEmail() {
        return senderEmail;
    }
    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
