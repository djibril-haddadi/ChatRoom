package com.example.demo;

import Repositories.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
public class InvitationController {
    @Autowired
    private InvitationRepository InvitationRepo;

    @GetMapping("/addInvitation")
    public String addInvitation() {
        return "addInvitation";
    }

    @PostMapping("/addInvitation")
    public ResponseEntity<String> addInvitation(@RequestParam("etat") Etat etat, @RequestParam("userInvite") User userInvite, @RequestParam("date") Date date){
        Invitation i1 = new Invitation(etat, userInvite, date);
        InvitationRepo.save(i1);
        return ResponseEntity.ok("Invitation added succesfully");
    }
}
