package com.example.demo.DTO;

import com.example.demo.*;

public class InvitationDTO{
    private Etat etat;
    private User userInvite;
    private User invited;

    //Getter et setter

    public Etat getEtat() {
        return etat;
    }
    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public User getUserInvite() {
        return userInvite;
    }
    public void setUserInvite(User userInvite) {
        this.userInvite = userInvite;
    }

    public User getInvited() {
        return invited;
    }
    public void setInvited(User invited) {
        this.invited = invited;
    }
}
