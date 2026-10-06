package com.djibrilhaddadi.chatrooms.dto;

public class InvitationResponseDTO extends EvenementResponseDTO{
    private String etat;
    private String invitedEmail;

    public InvitationResponseDTO(){}
    //Getter et setter

    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getInvitedEmail() {
        return invitedEmail;
    }

    public void setInvitedEmail(String invitedEmail) {
        this.invitedEmail = invitedEmail;
    }
}
