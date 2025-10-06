package com.example.demo;

import Repositories.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
public class InvitationController {
    @Autowired
    private InvitationRepository InvitationRepo;

    @GetMapping("/addInvitation")
    public String addInvitation() {
        return "addInvitation";
    }

    @PostMapping("/addInvitation")
    public ResponseEntity<String> addInvitation(
            @RequestParam("etat") Etat etat,
            @RequestParam("userInvite") User userInvite,
            @RequestParam("date") Date date){
        Invitation u1 = new Invitation(etat, userInvite, date);
        InvitationRepo.save(u1);
        return ResponseEntity.ok("Invitation created successfully.");
    }

    @GetMapping("/getInvitations")
    public ResponseEntity<List<Invitation>> getInvitation(){
        List<Invitation> invitationList = InvitationRepo.findAll();

        return ResponseEntity.ok(invitationList);
    }

    @GetMapping("/getInvitation")
    public ResponseEntity<Invitation> getInvitation(@RequestParam("id") long id){
        Invitation invitation = InvitationRepo.findById(id);

        return ResponseEntity.ok(invitation);
    }

    @PutMapping("/modifyInvitation")
    public ResponseEntity<String> modifyInvitation(@RequestBody Invitation invitationBody){
        Invitation invitation = InvitationRepo.findById(invitationBody.getId());
        if (invitation == null){return ResponseEntity.ok("Invitation does not exist, could not be modified");}
        InvitationRepo.save(invitationBody);
        return ResponseEntity.ok("Invitation modified successfully");
    }

    @DeleteMapping("/deleteInvitation")
    public ResponseEntity<String> deleteInvitation(@RequestParam("id") long id){
        Invitation invitation = InvitationRepo.findById(id);
        if (invitation == null){return ResponseEntity.ok("Invitation does not exist, could not be deleted");}
        InvitationRepo.delete(invitation);
        return ResponseEntity.ok("invitation deleted successfully");
    }
}
