package com.example.demo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Invitation extends Evenement{
    @Enumerated(EnumType.STRING)
    private Etat etat;
    @OneToOne
    private User userInvite;

    @ManyToOne
    @JoinColumn(name = "invited_email")
    private User invited;


    public Invitation(){}

    Invitation(long id,Etat newEtat, User newUserInvite, Date newDate){
        super( newDate);
        this.etat = newEtat;
        this.userInvite = newUserInvite;
    }

    public Invitation(Etat newEtat, User newUserInvite, Date newDate){
        super(newDate);
        this.etat = newEtat;
        this.userInvite = newUserInvite;
    }

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
