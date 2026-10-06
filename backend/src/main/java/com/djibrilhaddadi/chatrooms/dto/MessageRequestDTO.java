package com.djibrilhaddadi.chatrooms.dto;

public class MessageRequestDTO {

    // id to enable modification of a specific message
    private Long id;
    private String contenu;
    private String salonTitre;
    private String senderEmail;

    public MessageRequestDTO(){}
    public MessageRequestDTO(String contenu, String salonTitre, String senderEmail) {
        this.contenu = contenu;
        this.salonTitre = salonTitre;
        this.senderEmail = senderEmail;
    }

    //Getter et setter

    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getSalonTitre() {
        return salonTitre;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSalonTitre(String salonTitre) {
        this.salonTitre = salonTitre;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
