package com.example.demo.Controller;

import com.example.demo.Etat;
import com.example.demo.Invitation;
import com.example.demo.Service.InvitationService;
import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
public class InvitationController {
    @Autowired
    private InvitationService invitationService;

    @GetMapping("/Invitation/add")
    public String addInvitation() {
        return "addInvitation";
    }

    @PostMapping("/Invitation/add")
    public ResponseEntity<String> addInvitation(
            @RequestParam("etat") Etat etat,
            @RequestParam("userInvite") User userInvite,
            @RequestParam("date") Date date){
        return ResponseEntity.ok(invitationService.addInvitation(etat, userInvite, date));
    }

    @GetMapping("/Invitation/get")
    public ResponseEntity<List<Invitation>> getInvitation(){
        return ResponseEntity.ok(invitationService.getInvitation());
    }

    @GetMapping("/Invitation/getById")
    public ResponseEntity<Invitation> getInvitation(@RequestParam("id") long id){
        return ResponseEntity.ok(invitationService.getInvitation(id));
    }

    @PutMapping("/Invitation/modify")
    public ResponseEntity<String> modifyInvitation(@RequestBody Invitation invitationBody){
        return ResponseEntity.ok(invitationService.modifyInvitation(invitationBody));
    }

    @DeleteMapping("/Invitation/delete")
    public ResponseEntity<String> deleteInvitation(@RequestParam("id") long id){
        return ResponseEntity.ok(invitationService.deleteInvitation(id));
    }
}
