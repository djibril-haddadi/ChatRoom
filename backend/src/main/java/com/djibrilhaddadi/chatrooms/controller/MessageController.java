package com.djibrilhaddadi.chatrooms.controller;

import com.djibrilhaddadi.chatrooms.dto.MessageRequestDTO;
import com.djibrilhaddadi.chatrooms.dto.MessageResponseDTO;
import com.djibrilhaddadi.chatrooms.service.MessageService;
import com.djibrilhaddadi.chatrooms.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private SalonService salonService;

    @PostMapping("/Message/add")
    public ResponseEntity<String> addSalon(@RequestBody() MessageRequestDTO messageDTO){
        if (messageService.addMessage(messageDTO)){
            return ResponseEntity.ok("Message created successfully");
        }
        return ResponseEntity.internalServerError().body("Could not create Message");
    }

    @GetMapping("/Message/get")
    public ResponseEntity<List<MessageResponseDTO>> getMessage(){
        return ResponseEntity.ok(messageService.getMessage());
    }

    @GetMapping("/Message/getById")
    public ResponseEntity<MessageResponseDTO> getMessage(@RequestParam("id") long id){
        return ResponseEntity.ok(messageService.getMessage(id));
    }

    @PutMapping("/Message/modify")
    public ResponseEntity<String> modifyMessage(@RequestBody MessageRequestDTO messageBody){
        if (messageService.modifyMessage(messageBody)){
            return ResponseEntity.ok("Message modified sucessfully");
        }
        return ResponseEntity.status(404).body("Message could not be found");
    }

    @DeleteMapping("/Message/delete")
    public ResponseEntity<String> deleteMessage(@RequestParam("id") long id){
        return ResponseEntity.ok(messageService.deleteMessage(id));
    }

    @GetMapping("/Salon/{titre}/messages")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesBySalon(@PathVariable String titre) {
        return ResponseEntity.ok(messageService.getMessagesBySalonTitre(titre));
    }

}