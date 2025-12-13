package com.example.demo.Config;

import com.example.demo.DTO.MessageResponseDTO;
import com.example.demo.DTO.SalonResponseDTO;
import com.example.demo.DTO.UserResponseDTO;
import com.example.demo.Message;
import com.example.demo.Salon;
import com.example.demo.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Utiliser la configuration CORS définie ci-dessous
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/User/add", "/User/login").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // Autoriser l'origine de votre application React
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Autoriser les méthodes HTTP
        configuration.setAllowedHeaders(Arrays.asList("*")); // Autoriser tous les en-têtes
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Appliquer à tous les endpoints
        return source;
    }


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        //Salon
        mapper.typeMap(Salon.class, SalonResponseDTO.class)
                .addMapping(src -> src.getCreator().getPseudo(),
                        SalonResponseDTO::setCreatorPseudo);
        //message
        mapper.typeMap(Message.class, MessageResponseDTO.class)
                .addMapping(src -> src.getSender().getEmail(),
                        MessageResponseDTO::setSenderEmail);

        mapper.typeMap(User.class, UserResponseDTO.class)
                .addMapping(src -> src.getSalonActif() != null ? src.getSalonActif().getTitre() : null,
                        UserResponseDTO::setSalonActif);

        return mapper;
    }
}
