package com.djibrilhaddadi.chatrooms.service;

import com.djibrilhaddadi.chatrooms.dto.MessageRequestDTO;
import com.djibrilhaddadi.chatrooms.dto.MessageResponseDTO;
import com.djibrilhaddadi.chatrooms.entity.Message;
import com.djibrilhaddadi.chatrooms.entity.Salon;
import com.djibrilhaddadi.chatrooms.entity.User;
import com.djibrilhaddadi.chatrooms.repository.MessageRepository;
import com.djibrilhaddadi.chatrooms.repository.SalonRepository;
import com.djibrilhaddadi.chatrooms.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepo;
    @Mock
    private UserRepository userRepo;
    @Mock
    private SalonRepository salonRepo;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MessageService messageService;

    private User sender;
    private Salon salon;
    private MessageRequestDTO requestDto;

    @BeforeEach
    void setUp() {
        sender = new User("carol@test.com", "Durand", "Carol", "carol", "pwd");
        salon = new Salon("dev", sender);
        requestDto = new MessageRequestDTO("Hello team", "dev", "carol@test.com");
    }

    @Test
    void addMessage_returnsFalse_whenSenderMissing() {
        when(userRepo.findByEmail("carol@test.com")).thenReturn(null);
        when(salonRepo.findByTitre("dev")).thenReturn(salon);

        assertFalse(messageService.addMessage(requestDto));
        verify(messageRepo, never()).save(any(Message.class));
    }

    @Test
    void addMessage_returnsFalse_whenSalonMissing() {
        when(userRepo.findByEmail("carol@test.com")).thenReturn(sender);
        when(salonRepo.findByTitre("dev")).thenReturn(null);

        assertFalse(messageService.addMessage(requestDto));
        verify(messageRepo, never()).save(any(Message.class));
    }

    @Test
    void addMessage_savesMessage_whenSenderAndSalonExist() {
        when(userRepo.findByEmail("carol@test.com")).thenReturn(sender);
        when(salonRepo.findByTitre("dev")).thenReturn(salon);

        assertTrue(messageService.addMessage(requestDto));

        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepo).save(captor.capture());
        Message saved = captor.getValue();
        assertEquals("Hello team", saved.getContenu());
        assertEquals(sender, saved.getSender());
        assertEquals(salon, saved.getSalon());
        assertNotNull(saved.getDate());
    }

    @Test
    void getMessagesBySalonTitre_mapsRepositoryResults() {
        Message message = new Message();
        message.setContenu("Hello team");
        message.setSender(sender);
        message.setSalon(salon);
        message.setDate(new Date());

        MessageResponseDTO dto = new MessageResponseDTO("Hello team", message.getDate(), "carol@test.com");
        when(messageRepo.findBySalon_TitreOrderByDateAsc("dev")).thenReturn(List.of(message));
        when(modelMapper.map(message, MessageResponseDTO.class)).thenReturn(dto);

        List<MessageResponseDTO> result = messageService.getMessagesBySalonTitre("dev");

        assertEquals(1, result.size());
        assertEquals("Hello team", result.get(0).getContenu());
        assertEquals("carol@test.com", result.get(0).getSenderEmail());
    }

    @Test
    void deleteMessage_returnsError_whenMessageMissing() {
        when(messageRepo.findById(99L)).thenReturn(null);

        String result = messageService.deleteMessage(99L);

        assertTrue(result.toLowerCase().contains("does not exist"));
        verify(messageRepo, never()).delete(any(Message.class));
    }

    @Test
    void deleteMessage_deletes_whenMessageExists() {
        Message message = new Message();
        when(messageRepo.findById(1L)).thenReturn(message);

        String result = messageService.deleteMessage(1L);

        assertTrue(result.toLowerCase().contains("deleted"));
        verify(messageRepo).delete(message);
    }
}
