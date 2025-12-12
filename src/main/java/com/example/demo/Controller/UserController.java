package com.example.demo.Controller;

import com.example.demo.DTO.UserRequestDTO;
import com.example.demo.DTO.UserResponseDTO;
import com.example.demo.Service.UserService;
import com.example.demo.User;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userServ;

    @PostMapping("/User/add")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDTO userDto) {
        if (userServ.createUser(userDto)){
            return ResponseEntity.ok("User created successfully.");
        }
        return ResponseEntity.badRequest().body("User already existed, cannot be created twice");
    }

    @PostMapping("/User/login")
    public ResponseEntity<Map<String, String>> autentificationUser(@RequestBody UserRequestDTO userDto) throws AuthException {
        String token = userServ.authenticate(userDto);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
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