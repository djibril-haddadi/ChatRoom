package com.example.demo.DTO;

public class SuppressionRequestDTO extends EvenementRequestDTO {

    private String userSupprimeEmail;
    private String raison;

    public SuppressionRequestDTO() {}

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
