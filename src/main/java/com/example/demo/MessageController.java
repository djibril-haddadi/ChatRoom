package com.example.demo;

import Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/addMessage")
    public String addMessage() {
        return "addMessage";
    }

    @PostMapping("/addMessage")
    public ResponseEntity<String> addMessage(
            @RequestParam("contenu") String contenu,
            @RequestParam("date") Date date) {
        Message m1 = new Message(contenu, date);
        messageRepository.save(m1);
        return ResponseEntity.ok("Message added successfully.");
    }
}