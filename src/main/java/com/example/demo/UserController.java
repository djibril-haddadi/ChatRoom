package com.example.demo;

import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/addUser")
    public String addUser() {
        return "addUser";
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("pseudo") String pseudo,
            @RequestParam("email") String email,
            @RequestParam("mdp") String mdp) {
        User m1 = new User(email, nom, prenom, pseudo, mdp);
        userRepo.save(m1);
        return ResponseEntity.ok("User created successfully.");
    }
}