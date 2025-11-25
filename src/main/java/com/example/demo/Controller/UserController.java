package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userServ;

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
        userServ.createUser(u1);
        return ResponseEntity.ok("User created successfully.");
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUser(){
        return ResponseEntity.ok(userServ.getAllUsers());
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam("email") String email){
        return ResponseEntity.ok(userServ.getUser(email));
    }

    @PutMapping("/modifyUser")
    public ResponseEntity<String> modifyUser(@RequestBody User userBody){
        if (userServ.updateUser(userBody)){
            return ResponseEntity.ok("User modified successfully");
        }
        return ResponseEntity.ok("User could not be modified; it was not found");
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam("email") String email){
        if (userServ.deleteUser(email)){
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.ok("User could not be deleted; it was not found");
    }

}