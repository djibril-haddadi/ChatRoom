package com.example.demo.Controller;

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
        return ResponseEntity.ok(messageService.getMessage());
    }

    @GetMapping("/getMessage")
    public ResponseEntity<Message> getMessage(@RequestParam("id") long id){
        return ResponseEntity.ok(messageService.getMessage(id));
    }

    @PutMapping("/modifyMessage")
    public ResponseEntity<String> modifyMessage(@RequestBody Message messageBody){
        return ResponseEntity.ok(messageService.modifyMessage(messageBody));
    }

    @DeleteMapping("/deleteMessage")
    public ResponseEntity<String> deleteMessage(@RequestParam("id") long id){
        return ResponseEntity.ok(messageService.deleteMessage(id));
    }
}