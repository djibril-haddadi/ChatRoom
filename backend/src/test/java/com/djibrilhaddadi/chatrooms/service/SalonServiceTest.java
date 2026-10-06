package com.djibrilhaddadi.chatrooms.service;

import com.djibrilhaddadi.chatrooms.dto.SalonRequestDTO;
import com.djibrilhaddadi.chatrooms.dto.SalonResponseDTO;
import com.djibrilhaddadi.chatrooms.dto.UserResponseDTO;
import com.djibrilhaddadi.chatrooms.entity.Salon;
import com.djibrilhaddadi.chatrooms.entity.User;
import com.djibrilhaddadi.chatrooms.repository.SalonRepository;
import com.djibrilhaddadi.chatrooms.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalonServiceTest {

    @Mock
    private SalonRepository salonRepo;
    @Mock
    private UserRepository userRepo;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SalonService salonService;

    private User creator;
    private Salon salon;
    private SalonRequestDTO requestDto;

    @BeforeEach
    void setUp() {
        creator = new User("bob@test.com", "Martin", "Bob", "bob", "secret");
        salon = new Salon("general", creator);
        salon.setDescription("General room");
        salon.setUserList(List.of(creator));

        requestDto = new SalonRequestDTO("general", "General room", "bob@test.com");
    }

    @Test
    void addSalon_returnsFalse_whenCreatorDoesNotExist() {
        when(userRepo.findByEmail("bob@test.com")).thenReturn(null);

        assertFalse(salonService.addSalon(requestDto));
        verify(salonRepo, never()).save(any(Salon.class));
    }

    @Test
    void addSalon_savesSalon_whenCreatorExists() {
        when(userRepo.findByEmail("bob@test.com")).thenReturn(creator);
        when(modelMapper.map(requestDto, Salon.class)).thenReturn(salon);

        assertTrue(salonService.addSalon(requestDto));
        assertEquals(creator, salon.getCreator());
        verify(salonRepo).save(salon);
    }

    @Test
    void modifySalon_returnsFalse_whenSalonMissing() {
        when(salonRepo.findByTitre("general")).thenReturn(null);

        assertFalse(salonService.modifySalon(requestDto));
        verify(salonRepo, never()).save(any(Salon.class));
    }

    @Test
    void modifySalon_updatesAndSaves_whenSalonExists() {
        when(salonRepo.findByTitre("general")).thenReturn(salon);

        assertTrue(salonService.modifySalon(requestDto));
        verify(modelMapper).map(requestDto, salon);
        verify(salonRepo).save(salon);
    }

    @Test
    void deleteSalon_returnsFalse_whenSalonMissing() {
        when(salonRepo.findByTitre("missing")).thenReturn(null);

        assertFalse(salonService.deleteSalon("missing"));
        verify(salonRepo, never()).delete(any(Salon.class));
    }

    @Test
    void deleteSalon_deletes_whenSalonExists() {
        when(salonRepo.findByTitre("general")).thenReturn(salon);

        assertTrue(salonService.deleteSalon("general"));
        verify(salonRepo).delete(salon);
    }

    @Test
    void getSalonMembers_throws_whenSalonMissing() {
        when(salonRepo.findByTitre("missing")).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> salonService.getSalonMembers("missing"));
    }

    @Test
    void getSalonMembers_mapsUsers_whenSalonExists() {
        UserResponseDTO mapped = new UserResponseDTO();
        mapped.setEmail("bob@test.com");
        when(salonRepo.findByTitre("general")).thenReturn(salon);
        when(modelMapper.map(creator, UserResponseDTO.class)).thenReturn(mapped);

        List<UserResponseDTO> members = salonService.getSalonMembers("general");

        assertEquals(1, members.size());
        assertEquals("bob@test.com", members.get(0).getEmail());
    }

    @Test
    void getSalon_mapsEntityToDto() {
        SalonResponseDTO dto = new SalonResponseDTO("general", "General room", "bob");
        when(salonRepo.findByTitre("general")).thenReturn(salon);
        when(modelMapper.map(salon, SalonResponseDTO.class)).thenReturn(dto);

        SalonResponseDTO result = salonService.getSalon("general");

        assertEquals("general", result.getTitre());
        assertEquals("bob", result.getCreatorPseudo());
    }
}
