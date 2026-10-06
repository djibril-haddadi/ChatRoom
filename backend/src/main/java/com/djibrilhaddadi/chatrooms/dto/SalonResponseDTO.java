package com.djibrilhaddadi.chatrooms.dto;

public class SalonResponseDTO {

    private String titre;
    private String description;
    private String creatorPseudo;

    public SalonResponseDTO(){}
    public SalonResponseDTO(String titre, String description, String creator){
        this.titre = titre;
        this.description = description;
        this.creatorPseudo = creator;
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

    public String getCreatorPseudo() {
        return this.creatorPseudo;
    }
    public void setCreatorPseudo(String creatorPseudo) {
        this.creatorPseudo = creatorPseudo;
    }
}
