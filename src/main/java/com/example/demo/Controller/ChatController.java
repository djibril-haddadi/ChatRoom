package com.example.demo.Controller;

import DTO.ChatMessage;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate template) {
        this.messagingTemplate = template;
    }

    /**
     * Le client envoie vers : /app/chat.send
     * Nous renvoyons vers : /topic/{room}
     */
    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessage message) {

        // Destination dynamique en fonction de la room
        String destination = "/topic/" + message.getRoom();

        // Format : "login: message"
        String formattedMsg = message.getLogin() + ": " + message.getContent();

        // Envoi à tous les abonnés de /topic/{room}
        messagingTemplate.convertAndSend(destination, formattedMsg);
    }
}
