package com.example.demo.Controller;

import com.example.demo.DTO.UserRequestDTO;
import com.example.demo.DTO.UserResponseDTO;
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

    @PostMapping("/User/addParams")
    public ResponseEntity<String> addUserParams(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("pseudo") String pseudo,
            @RequestParam("email") String email,
            @RequestParam("mdp") String mdp) {
        UserRequestDTO u1 = new UserRequestDTO(email, nom, prenom, pseudo, mdp);
        userServ.createUser(u1);
        return ResponseEntity.ok("User created successfully.");
    }

    @PostMapping("/User/add")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDTO userDto) {
        userServ.createUser(userDto);
        return ResponseEntity.ok("User created successfully.");
    }

    @GetMapping("/User/get")
    public ResponseEntity<List<UserResponseDTO>> getUser(){
        return ResponseEntity.ok(userServ.getAllUsers());
    }

    @GetMapping("/User/getByEmail")
    public ResponseEntity<UserResponseDTO> getUser(@RequestParam("email") String email){
        UserResponseDTO user = userServ.getUser(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/User/modify")
    public ResponseEntity<String> modifyUser(@RequestBody UserRequestDTO userBody){
        if (userServ.updateUser(userBody)){
            return ResponseEntity.ok("User modified successfully");
        }
        return ResponseEntity.status(404).body("User could not be modified; it was not found");
    }

    @DeleteMapping("/User/delete")
    public ResponseEntity<String> deleteUser(@RequestParam("email") String email){
        if (userServ.deleteUser(email)){
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.status(404).body("User could not be deleted; it was not found");
    }

}