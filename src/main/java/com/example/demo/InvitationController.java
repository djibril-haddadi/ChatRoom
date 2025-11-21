package com.example.demo;

import Repositories.InvitationRepository;
import Service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
public class InvitationController {
    @Autowired
    private InvitationService invitationService;

    @GetMapping("/addInvitation")
    public String addInvitation() {
        return "addInvitation";
    }

    @PostMapping("/addInvitation")
    public ResponseEntity<String> addInvitation(
            @RequestParam("etat") Etat etat,
            @RequestParam("userInvite") User userInvite,
            @RequestParam("date") Date date){
        return ResponseEntity.ok(invitationService.addInvitation(etat, userInvite, date));
    }

    @GetMapping("/getInvitations")
    public ResponseEntity<List<Invitation>> getInvitation(){
        return ResponseEntity.ok(invitationService.getInvitation());
    }

    @GetMapping("/getInvitation")
    public ResponseEntity<Invitation> getInvitation(@RequestParam("id") long id){
        return ResponseEntity.ok(invitationService.getInvitation(id));
    }

    @PutMapping("/modifyInvitation")
    public ResponseEntity<String> modifyInvitation(@RequestBody Invitation invitationBody){
        return ResponseEntity.ok(invitationService.modifyInvitation(invitationBody));
    }

    @DeleteMapping("/deleteInvitation")
    public ResponseEntity<String> deleteInvitation(@RequestParam("id") long id){
        return ResponseEntity.ok(invitationService.deleteInvitation(id));
    }
}
