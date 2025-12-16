package com.example.demo.Controller;

import com.example.demo.DTO.MessageResponseDTO;
import com.example.demo.DTO.SalonResponseDTO;
import com.example.demo.DTO.UserRequestDTO;
import com.example.demo.DTO.UserResponseDTO;
import com.example.demo.Service.SalonService;
import com.example.demo.Service.UserService;
import com.example.demo.User;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userServ;

    @Autowired
    private SalonService salonServ;

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

    @GetMapping("/Salon/{titre}/user")
    public ResponseEntity<List<UserResponseDTO>> getMembersBySalon(@PathVariable String titre) {
        return ResponseEntity.ok(salonServ.getSalonMembers(titre));
    }

    @GetMapping("/Salon/{titre}/userConnected")
    public ResponseEntity<List<UserResponseDTO>> getMembersConnectedBySalon(@PathVariable String titre) {
        return ResponseEntity.ok(userServ.getSalonMembersConnected(titre));
    }

    @PutMapping("/User/{email}/active")
    public ResponseEntity<String> setActiveUser(@PathVariable String email, @RequestParam boolean active) {
        if (!userServ.setActive(email,active)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("User active updated");
    }

    /*@PutMapping("/User/{email}/setSalonActif")
    public ResponseEntity<String> setActiveSalon(@PathVariable String email, @RequestParam String titre) {
        if (!userServ.setActiveSalon(email,titre)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("User active updated");
    }*/
    @PutMapping("/User/{email}/setSalonActif")
    public ResponseEntity<Map<String, String>> setActiveSalon(
            @PathVariable String email,
            @RequestBody Map<String, String> request) {

        Map<String, String> response = new HashMap<>();

        try {
            String titre = request.get("titre");
            if (titre == null || titre.trim().isEmpty()) {
                response.put("error", "Le paramètre 'titre' est obligatoire");
                return ResponseEntity.badRequest().body(response);
            }

            if (!userServ.setActiveSalon(email, titre)) {
                response.put("error", "Impossible de mettre à jour le salon actif");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.put("message", "Salon actif mis à jour avec succès");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Erreur serveur: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/User/{email}/clearSalonActif")
    public ResponseEntity<Void> clearActiveSalon(@PathVariable String email) {
        return userServ.clearActiveSalon(email) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/User/{email}/acceptInvitation")
    public ResponseEntity<Map<String, String>> acceptInvitation(
            @PathVariable String email,
            @RequestBody Map<String, String> request) {

        Map<String, String> response = new HashMap<>();

        try {
            String salonTitre = request.get("salonTitre");
            if (salonTitre == null || salonTitre.trim().isEmpty()) {
                response.put("error", "Le titre du salon est obligatoire");
                return ResponseEntity.badRequest().body(response);
            }

            boolean success = userServ.acceptInvitation(email, salonTitre);
            if (!success) {
                response.put("error", "Impossible d'accepter l'invitation");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

            response.put("message", "Invitation acceptée avec succès");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Erreur: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}