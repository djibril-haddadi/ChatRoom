package com.example.demo.Controller;

import com.example.demo.DTO.MessageDTO;
import com.example.demo.Message;
import com.example.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/Message/add")
    public String addMessage() {
        return "addMessage";
    }

    @PostMapping("/Message/add")
    public ResponseEntity<String> addMessage(
            @RequestParam("contenu") String contenu,
            @RequestParam("date") Date date) {
        return ResponseEntity.ok("Message added successfully.");
    }

    @GetMapping("/Messages/get")
    public ResponseEntity<List<Message>> getMessage(){
        return ResponseEntity.ok(messageService.getMessage());
    }

    @GetMapping("/Message/getById")
    public ResponseEntity<Message> getMessage(@RequestParam("id") long id){
        return ResponseEntity.ok(messageService.getMessage(id));
    }

    @PutMapping("/Message/modify")
    public ResponseEntity<String> modifyMessage(@RequestBody Message messageBody){
        return ResponseEntity.ok(messageService.modifyMessage(messageBody));
    }

    @DeleteMapping("/Message/delete")
    public ResponseEntity<String> deleteMessage(@RequestParam("id") long id){
        return ResponseEntity.ok(messageService.deleteMessage(id));
    }

    @GetMapping("/salons/{titre}/messages")
    public ResponseEntity<List<MessageDTO>> getMessagesBySalon(@PathVariable String titre) {
        List<MessageDTO> messages = messageService.getMessagesBySalonTitre(titre);
        return ResponseEntity.ok(messages);
    }

}