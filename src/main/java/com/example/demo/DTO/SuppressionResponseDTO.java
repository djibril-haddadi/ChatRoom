package com.example.demo.DTO;

public class SuppressionResponseDTO extends EvenementResponseDTO {

    private String userSupprimeEmail;
    private String raison;

    public SuppressionResponseDTO() {}

    public String getUserSupprimeEmail() {
        return userSupprimeEmail;
    }

    public void setUserSupprimeEmail(String userSupprimeEmail) {
        this.userSupprimeEmail = userSupprimeEmail;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }
}
