package com.djibrilhaddadi.chatrooms.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider(
                "ChatRoomUnitTestSecretKey_AtLeast32Chars!!",
                3_600_000L
        );
    }

    @Test
    void generateToken_containsSubjectEmail() {
        String token = jwtTokenProvider.generateToken("alice@test.com");

        assertNotNull(token);
        assertFalse(token.isBlank());
        assertEquals("alice@test.com", jwtTokenProvider.getEmailFromToken(token));
    }

    @Test
    void validateToken_returnsTrue_forFreshToken() {
        String token = jwtTokenProvider.generateToken("bob@test.com");

        assertTrue(jwtTokenProvider.validateToken(token));
    }

    @Test
    void validateToken_returnsFalse_forTamperedToken() {
        String token = jwtTokenProvider.generateToken("bob@test.com");

        assertFalse(jwtTokenProvider.validateToken(token + "x"));
    }
}
