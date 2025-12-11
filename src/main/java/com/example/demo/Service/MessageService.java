package com.example.demo.Service;

import Repositories.SalonRepository;
import Repositories.UserRepository;
import com.example.demo.*;
import Repositories.MessageRepository;
import com.example.demo.DTO.MessageRequestDTO;
import com.example.demo.DTO.MessageResponseDTO;
import com.example.demo.Message;
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

        Message message = modelMapper.map(MessageDTO, Message.class);
        message.setSender(creator);
        message.setSalon(salon);

        messageRepo.save(message);
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
        Message message = messageRepo.findById(messageBody.getId());
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
