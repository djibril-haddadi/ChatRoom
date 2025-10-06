package com.example.demo;

import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        User u1 = new User(email, nom, prenom, pseudo, mdp);
        userRepo.save(u1);
        return ResponseEntity.ok("User created successfully.");
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUser(){
        List<User> userList = userRepo.findAll();

        return ResponseEntity.ok(userList);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam("email") String email){
        User user = userRepo.findByEmail(email);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/modifyUser")
    public ResponseEntity<String> modifyUser(@RequestBody User userBody){
        User user = userRepo.findByEmail(userBody.getEmail());
        if (user == null){return ResponseEntity.ok("User does not exist, could not be modified");}
        userRepo.save(userBody);
        return ResponseEntity.ok("User modified successfully");
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam("email") String email){
        User user = userRepo.findByEmail(email);
        if (user == null){return ResponseEntity.ok("User does not exist, could not be deleted");}
        userRepo.delete(user);
        return ResponseEntity.ok("user deleted successfully");
    }

}