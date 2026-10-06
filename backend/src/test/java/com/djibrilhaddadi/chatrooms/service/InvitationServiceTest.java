package com.djibrilhaddadi.chatrooms.service;

import com.djibrilhaddadi.chatrooms.dto.InvitationRequestDTO;
import com.djibrilhaddadi.chatrooms.entity.Etat;
import com.djibrilhaddadi.chatrooms.entity.Invitation;
import com.djibrilhaddadi.chatrooms.entity.Salon;
import com.djibrilhaddadi.chatrooms.entity.User;
import com.djibrilhaddadi.chatrooms.repository.InvitationRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvitationServiceTest {

    @Mock
    private InvitationRepository invitationRepo;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private SalonRepository salonRepo;
    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private InvitationService invitationService;

    private InvitationRequestDTO requestDto;
    private Salon salon;
    private User invited;

    @BeforeEach
    void setUp() {
        requestDto = new InvitationRequestDTO();
        requestDto.setSalonTitre("general");
        requestDto.setInvitedEmail("dave@test.com");

        User creator = new User("owner@test.com", "Owner", "Own", "owner", "pwd");
        salon = new Salon("general", creator);
        invited = new User("dave@test.com", "Dave", "D", "dave", "pwd");
    }

    @Test
    void addInvitation_returnsFalse_whenSalonMissing() {
        when(salonRepo.findByTitre("general")).thenReturn(null);

        assertFalse(invitationService.addInvitation(requestDto));
        verify(invitationRepo, never()).save(any(Invitation.class));
    }

    @Test
    void addInvitation_returnsFalse_whenInvitedUserMissing() {
        when(salonRepo.findByTitre("general")).thenReturn(salon);
        when(userRepo.findByEmail("dave@test.com")).thenReturn(null);

        assertFalse(invitationService.addInvitation(requestDto));
        verify(invitationRepo, never()).save(any(Invitation.class));
    }

    @Test
    void addInvitation_savesPendingInvitation_whenDataIsValid() {
        when(salonRepo.findByTitre("general")).thenReturn(salon);
        when(userRepo.findByEmail("dave@test.com")).thenReturn(invited);

        assertTrue(invitationService.addInvitation(requestDto));

        ArgumentCaptor<Invitation> captor = ArgumentCaptor.forClass(Invitation.class);
        verify(invitationRepo).save(captor.capture());
        Invitation saved = captor.getValue();
        assertEquals(salon, saved.getSalon());
        assertEquals(invited, saved.getInvited());
        assertEquals(Etat.EN_ATTENTE, saved.getEtat());
        assertNotNull(saved.getDate());
    }
}
