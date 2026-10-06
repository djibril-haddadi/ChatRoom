package com.djibrilhaddadi.chatrooms.controller;

import com.djibrilhaddadi.chatrooms.dto.InvitationRequestDTO;
import com.djibrilhaddadi.chatrooms.dto.InvitationResponseDTO;
import com.djibrilhaddadi.chatrooms.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @PostMapping("/Invitation/add")
    public ResponseEntity<String> addInvitation(@RequestBody InvitationRequestDTO invitationDTO) {
        if (invitationService.addInvitation(invitationDTO)) {
            return ResponseEntity.ok("Invitation created successfully");
        }
        return ResponseEntity.status(404).body("Salon not found, could not create invitation");
    }

    @GetMapping("/Invitation/get")
    public ResponseEntity<List<InvitationResponseDTO>> getInvitation() {
        return ResponseEntity.ok(invitationService.getInvitation());
    }

    @GetMapping("/Invitation/getById")
    public ResponseEntity<InvitationResponseDTO> getInvitation(@RequestParam("id") long id) {
        InvitationResponseDTO invitation = invitationService.getInvitation(id);
        if (invitation == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(invitation);
    }

    @PutMapping("/Invitation/modify")
    public ResponseEntity<String> modifyInvitation(@RequestBody InvitationRequestDTO invitationBody) {
        if (invitationService.modifyInvitation(invitationBody)) {
            return ResponseEntity.ok("Invitation modified successfully");
        }
        return ResponseEntity.status(404).body("Invitation could not be found");
    }

    @DeleteMapping("/Invitation/delete")
    public ResponseEntity<String> deleteInvitation(@RequestParam("id") long id) {
        if (invitationService.deleteInvitation(id)) {
            return ResponseEntity.ok("Invitation deleted successfully");
        }
        return ResponseEntity.status(404).body("Invitation could not be found");
    }
}
