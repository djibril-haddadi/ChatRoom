package com.example.demo.Service;

import Repositories.SalonRepository;
import com.example.demo.*;
import Repositories.MessageRepository;
import com.example.demo.DTO.MessageDTO;
import com.example.demo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepo;

    public String addMessage() {
        return "addMessage";
    }

    public String addMessage(String contenu, Date date) {
        Message m1 = new Message(contenu, date);
        messageRepo.save(m1);
        return ("Message added successfully.");
    }

    public List<Message> getMessage(){
        List<Message> messageList = messageRepo.findAll();

        return (messageList);
    }

    public Message getMessage(long id){
        Message message = messageRepo.findById(id);

        return (message);
    }

    public String modifyMessage(Message messageBody){
        Message message = messageRepo.findById(messageBody.getId());
        if (message == null){return ("Message does not exist, could not be modified");}
        messageRepo.save(messageBody);
        return ("Message modified successfully");
    }

    public String deleteMessage(long id){
        Message message = messageRepo.findById(id);
        if (message == null){return ("Message does not exist, could not be deleted");}
        messageRepo.delete(message);
        return ("message deleted successfully");
    }

    public List<MessageDTO> getMessagesBySalonTitre(String titre) {
        List<Message> messages = messageRepo.findBySalon_TitreOrderByDateAsc(titre);

        return messages.stream().map(MessageDTO::new).toList();
    }
}
