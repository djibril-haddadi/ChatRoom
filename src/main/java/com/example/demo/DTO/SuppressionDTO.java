package com.example.demo.DTO;

import com.example.demo.User;

public class SuppressionDTO extends EvenementDTO{
    private String raison;
    private User userSupprime;

    //Getter et setter

    public String getRaison() {
        return raison;
    }
    public void setRaison(String raison) {
        this.raison = raison;
    }

    public User getUserSupprime() {
        return userSupprime;
    }
    public void setUserSupprime(User userSupprime) {
        this.userSupprime = userSupprime;
    }
}
