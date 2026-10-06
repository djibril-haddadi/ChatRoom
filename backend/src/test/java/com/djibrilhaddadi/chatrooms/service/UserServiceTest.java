package com.djibrilhaddadi.chatrooms.service;

import com.djibrilhaddadi.chatrooms.dto.UserRequestDTO;
import com.djibrilhaddadi.chatrooms.dto.UserResponseDTO;
import com.djibrilhaddadi.chatrooms.entity.User;
import com.djibrilhaddadi.chatrooms.repository.SalonRepository;
import com.djibrilhaddadi.chatrooms.repository.UserRepository;
import com.djibrilhaddadi.chatrooms.security.JwtTokenProvider;
import jakarta.security.auth.message.AuthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepo;
    @Mock
    private SalonRepository salonRepo;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private UserRequestDTO requestDto;
    private User user;

    @BeforeEach
    void setUp() {
        requestDto = new UserRequestDTO(
                "alice@test.com",
                "Dupont",
                "Alice",
                "alice",
                "password123"
        );
        user = new User("alice@test.com", "Dupont", "Alice", "alice", "encoded");
        user.setActive(true);
    }

    @Test
    void createUser_returnsFalse_whenEmailAlreadyExists() {
        when(userRepo.findByEmail("alice@test.com")).thenReturn(user);

        boolean created = userService.createUser(requestDto);

        assertFalse(created);
        verify(userRepo, never()).save(any(User.class));
    }

    @Test
    void createUser_savesEncodedPassword_whenEmailIsNew() {
        when(userRepo.findByEmail("alice@test.com")).thenReturn(null);
        when(modelMapper.map(requestDto, User.class)).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("hashed-password");

        boolean created = userService.createUser(requestDto);

        assertTrue(created);
        assertEquals("hashed-password", user.getMdp());
        assertTrue(user.isActive());
        verify(userRepo).save(user);
    }

    @Test
    void authenticate_returnsJwt_whenCredentialsAreValid() throws AuthException {
        when(userRepo.findByEmail("alice@test.com")).thenReturn(user);
        when(passwordEncoder.matches("password123", "encoded")).thenReturn(true);
        when(jwtTokenProvider.generateToken("alice@test.com")).thenReturn("jwt-token");

        String token = userService.authenticate(requestDto);

        assertEquals("jwt-token", token);
    }

    @Test
    void authenticate_throws_whenUserNotFound() {
        when(userRepo.findByEmail("alice@test.com")).thenReturn(null);

        assertThrows(AuthException.class, () -> userService.authenticate(requestDto));
    }

    @Test
    void authenticate_throws_whenPasswordIsWrong() {
        when(userRepo.findByEmail("alice@test.com")).thenReturn(user);
        when(passwordEncoder.matches("password123", "encoded")).thenReturn(false);

        assertThrows(AuthException.class, () -> userService.authenticate(requestDto));
    }

    @Test
    void getUser_returnsDto_whenUserExists() {
        when(userRepo.findById("alice@test.com")).thenReturn(Optional.of(user));

        UserResponseDTO dto = userService.getUser("alice@test.com");

        assertEquals("alice@test.com", dto.getEmail());
        assertEquals("alice", dto.getPseudo());
        assertTrue(dto.isActive());
    }
}
