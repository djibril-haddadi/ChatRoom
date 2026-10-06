package com.djibrilhaddadi.chatrooms.dto;

public class InvitationRequestDTO extends EvenementRequestDTO {
    private String invitedEmail;

    public InvitationRequestDTO() {}

    public String getInvitedEmail() {
        return invitedEmail;
    }

    public void setInvitedEmail(String invitedEmail) {
        this.invitedEmail = invitedEmail;
    }
}
