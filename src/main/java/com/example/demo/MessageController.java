package com.example.demo;

import Service.InvitationService;
import Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/addMessage")
    public String addMessage() {
        return "addMessage";
    }

    @PostMapping("/addMessage")
    public ResponseEntity<String> addMessage(
            @RequestParam("contenu") String contenu,
            @RequestParam("date") Date date) {
        return ResponseEntity.ok("Message added successfully.");
    }

    @GetMapping("/getMessages")
    public ResponseEntity<List<Message>> getMessage(){
        List<Message> messageList = messageService.getMessage();

        return ResponseEntity.ok(messageList);
    }

    @GetMapping("/getMessage")
    public ResponseEntity<Message> getMessage(@RequestParam("id") long id){
        Message message = messageService.getMessage(id);

        return ResponseEntity.ok(message);
    }

    @PutMapping("/modifyMessage")
    public ResponseEntity<String> modifyMessage(@RequestBody Message messageBody){
        Message message = messageService.getMessage(messageBody.getId());
        if (message == null){return ResponseEntity.ok("Message does not exist, could not be modified");}
        messageService.modifyMessage(messageBody);
        return ResponseEntity.ok("Message modified successfully");
    }

    @DeleteMapping("/deleteMessage")
    public ResponseEntity<String> deleteMessage(@RequestParam("id") long id){
        Message message = messageService.getMessage(id);
        if (message == null){return ResponseEntity.ok("Message does not exist, could not be deleted");}
        messageService.deleteMessage(message.getId());
        return ResponseEntity.ok("message deleted successfully");
    }
}