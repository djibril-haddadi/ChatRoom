package com.example.demo.Service;

import Repositories.SalonRepository;
import Repositories.SuppressionRepository;
import Repositories.UserRepository;
import com.example.demo.DTO.SuppressionRequestDTO;
import com.example.demo.DTO.SuppressionResponseDTO;
import com.example.demo.Salon;
import com.example.demo.Suppression;
import com.example.demo.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SuppressionService {

    @Autowired
    private SuppressionRepository suppressionRepo;

    @Autowired
    private SalonRepository salonRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public boolean addSuppression(SuppressionRequestDTO suppressionDTO) {
        Salon salon = salonRepo.findByTitre(suppressionDTO.getSalonTitre());
        if (salon == null) {
            return false;
        }

        User userSupprime = userRepo.findByEmail(suppressionDTO.getUserSupprimeEmail());
        if (userSupprime == null) {
            return false;
        }

        Suppression suppression = modelMapper.map(suppressionDTO, Suppression.class);
        suppression.setSalon(salon);
        suppression.setUserSupprime(userSupprime);
        suppression.setDate(new Date());

        suppressionRepo.save(suppression);
        return true;
    }

    public List<SuppressionResponseDTO> getSuppression() {
        return suppressionRepo.findAll().stream()
                .map(s -> modelMapper.map(s, SuppressionResponseDTO.class))
                .toList();
    }

    public SuppressionResponseDTO getSuppression(long id) {
        Suppression suppression = suppressionRepo.findById(id);
        if (suppression == null) {
            return null;
        }
        return modelMapper.map(suppression, SuppressionResponseDTO.class);
    }

    public boolean modifySuppression(SuppressionRequestDTO suppressionBody) {
        Suppression suppression = suppressionRepo.findById(suppressionBody.getId());
        if (suppression == null) {
            return false;
        }
        modelMapper.map(suppressionBody, suppression);

        suppressionRepo.save(suppression);
        return true;
    }

    public boolean deleteSuppression(long id) {
        Suppression suppression = suppressionRepo.findById(id);
        if (suppression == null) {
            return false;
        }
        suppressionRepo.delete(suppression);
        return true;
    }
}
