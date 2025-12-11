package com.example.demo.Config;

import com.example.demo.DTO.MessageResponseDTO;
import com.example.demo.DTO.SalonRequestDTO;
import com.example.demo.DTO.SalonResponseDTO;
import com.example.demo.Message;
import com.example.demo.Salon;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
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
        return mapper;
    }
}
