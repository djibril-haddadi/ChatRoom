package com.djibrilhaddadi.chatrooms.dto;

public class SalonRequestDTO {

    private String titre;
    private String description;
    private String creatorEmail;

    public SalonRequestDTO(){}
    public SalonRequestDTO(String titre, String description, String creator){
        this.titre = titre;
        this.description = description;
        this.creatorEmail = creator;
    }

    //Getter et setter
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorEmail() {
        return this.creatorEmail;
    }
    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }
}
