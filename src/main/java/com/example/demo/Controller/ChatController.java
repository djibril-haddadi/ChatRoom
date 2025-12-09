package com.example.demo.Controller;

import com.example.demo.DTO.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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
     * Le client envoie vers : /app/room/{roomId}/send
     * Nous renvoyons vers : /topic/room/{roomId}
     */
    @MessageMapping("/room/{roomId}/send")
    public void sendMessage(@DestinationVariable String roomId,
                            @Payload ChatMessage message) {

        // On force la room côté serveur pour éviter les triches
        message.setRoom(roomId);

        // Format d’affichage : "login: message"
        String formattedMsg = message.getLogin() + ": " + message.getContent();

        // Destination dédiée au salon demandé
        String destination = "/topic/room/" + roomId;

        // Envoi à tous les abonnés de /topic/room/{roomId}
        messagingTemplate.convertAndSend(destination, formattedMsg);
    }
}
