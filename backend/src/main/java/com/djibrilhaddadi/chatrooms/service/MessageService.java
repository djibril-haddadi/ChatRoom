package com.djibrilhaddadi.chatrooms.service;

import com.djibrilhaddadi.chatrooms.repository.SalonRepository;
import com.djibrilhaddadi.chatrooms.repository.UserRepository;
import com.djibrilhaddadi.chatrooms.entity.*;
import com.djibrilhaddadi.chatrooms.repository.MessageRepository;
import com.djibrilhaddadi.chatrooms.dto.MessageRequestDTO;
import com.djibrilhaddadi.chatrooms.dto.MessageResponseDTO;
import com.djibrilhaddadi.chatrooms.entity.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SalonRepository salonRepo;

    @Autowired
    private ModelMapper modelMapper;

    public boolean addMessage(MessageRequestDTO MessageDTO) {
        User creator = userRepo.findByEmail(MessageDTO.getSenderEmail());
        Salon salon = salonRepo.findByTitre(MessageDTO.getSalonTitre());

        if (creator == null || salon == null) {
            return false;
        }

        Message m = new Message();
        m.setContenu(MessageDTO.getContenu());
        m.setSender(creator);
        m.setSalon(salon);
        m.setDate(new Date());
        messageRepo.save(m);
        return true;
    }

    public List<MessageResponseDTO> getMessage(){
        return messageRepo.findAll().stream()
                .map(m -> modelMapper.map(m, MessageResponseDTO.class))
                .toList();
    }

    public MessageResponseDTO getMessage(long id){
        return modelMapper.map(messageRepo.findById(id), MessageResponseDTO.class);
    }

    public boolean modifyMessage(MessageRequestDTO messageBody){
        Message message = messageRepo.findById(messageBody.getId())
                .orElseThrow(() -> new RuntimeException("Message not found: " + messageBody.getId()));
        if (message == null){return false;}
        modelMapper.map(messageBody, message);
        messageRepo.save(message);
        return true;
    }

    public String deleteMessage(long id){
        Message message = messageRepo.findById(id);
        if (message == null){return ("Message does not exist, could not be deleted");}
        messageRepo.delete(message);
        return ("message deleted successfully");
    }

    public List<MessageResponseDTO> getMessagesBySalonTitre(String titre) {
        return messageRepo.findBySalon_TitreOrderByDateAsc(titre)
                .stream()
                .map(m -> modelMapper.map(m, MessageResponseDTO.class))
                .toList();
    }
}
