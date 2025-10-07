package com.example.demo;

import Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageRepository messageRepo;

    @GetMapping("/addMessage")
    public String addMessage() {
        return "addMessage";
    }

    @PostMapping("/addMessage")
    public ResponseEntity<String> addMessage(
            @RequestParam("contenu") String contenu,
            @RequestParam("date") Date date) {
        Message m1 = new Message(contenu, date);
        messageRepo.save(m1);
        return ResponseEntity.ok("Message added successfully.");
    }

    @GetMapping("/getMessages")
    public ResponseEntity<List<Message>> getMessage(){
        List<Message> messageList = messageRepo.findAll();

        return ResponseEntity.ok(messageList);
    }

    @GetMapping("/getMessage")
    public ResponseEntity<Message> getMessage(@RequestParam("id") long id){
        Message message = messageRepo.findById(id);

        return ResponseEntity.ok(message);
    }

    @PutMapping("/modifyMessage")
    public ResponseEntity<String> modifyMessage(@RequestBody Message messageBody){
        Message message = messageRepo.findById(messageBody.getId());
        if (message == null){return ResponseEntity.ok("Message does not exist, could not be modified");}
        messageRepo.save(messageBody);
        return ResponseEntity.ok("Message modified successfully");
    }

    @DeleteMapping("/deleteMessage")
    public ResponseEntity<String> deleteMessage(@RequestParam("id") long id){
        Message message = messageRepo.findById(id);
        if (message == null){return ResponseEntity.ok("Message does not exist, could not be deleted");}
        messageRepo.delete(message);
        return ResponseEntity.ok("message deleted successfully");
    }
}