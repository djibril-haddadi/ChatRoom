package com.example.demo.Service;

import Repositories.SalonRepository;
import Repositories.UserRepository;
import com.example.demo.DTO.SalonRequestDTO;
import com.example.demo.DTO.SalonResponseDTO;
import com.example.demo.DTO.UserResponseDTO;
import com.example.demo.Salon;
import com.example.demo.User;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class SalonService {
    @Autowired
    private SalonRepository salonRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public boolean addSalon(SalonRequestDTO salonDTO){
        User creator = userRepo.findByEmail(salonDTO.getCreatorEmail());
        if (creator == null) {
            return false;
        }

        Salon salon = modelMapper.map(salonDTO, Salon.class);
        salon.setCreator(creator);

        salonRepo.save(salon);
        return true;
    }

    public List<SalonResponseDTO> getSalon(){
        return salonRepo.findAll().stream()
                .map(s -> modelMapper.map(s, SalonResponseDTO.class))
                .toList();
    }

    public SalonResponseDTO getSalon(String titre){
        return modelMapper.map(salonRepo.findByTitre(titre), SalonResponseDTO.class);
    }

    public boolean modifySalon(SalonRequestDTO salonBody){
        Salon salon = salonRepo.findByTitre(salonBody.getTitre());
        if (salon == null){return false;}
        modelMapper.map(salonBody, salon);
        salonRepo.save(salon);
        return true;
    }

    public boolean deleteSalon(String titre){
        Salon salon = salonRepo.findByTitre(titre);
        if (salon == null){return false;}
        salonRepo.delete(salon);
        return true;
    }

    public List<UserResponseDTO> getSalonMembers(String titre){
        Salon salon = salonRepo.findByTitre(titre);
        if (salon == null){
            throw new EntityNotFoundException("Salon not found with titre: " + titre);
        }
        return salon.getUserList().stream()
                .map(u -> modelMapper.map(u, UserResponseDTO.class))
                .toList();
    }
}
