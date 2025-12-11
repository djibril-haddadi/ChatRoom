package com.example.demo.DTO;

import com.example.demo.*;

public class InvitationRequestDTO extends EvenementRequestDTO{
    private String invitedEmail;

    public InvitationRequestDTO(){}
    //Getter et setter

    public String getInvitedEmail() {
        return invitedEmail;
    }

    public void setInvitedEmail(String invitedEmail) {
        this.invitedEmail = invitedEmail;
    }
}
