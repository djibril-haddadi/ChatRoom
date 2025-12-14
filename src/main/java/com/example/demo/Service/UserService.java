package com.example.demo.Service;

import Repositories.SalonRepository;
import Repositories.UserRepository;
import com.example.demo.DTO.SalonResponseDTO;
import com.example.demo.DTO.UserRequestDTO;
import com.example.demo.DTO.UserResponseDTO;
import com.example.demo.Salon;
import com.example.demo.Security.JwtTokenProvider;
import com.example.demo.User;
import jakarta.security.auth.message.AuthException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SalonRepository salonRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(UserRequestDTO userDto){
        // Vérifier si l'email existe déjà
        User user = userRepo.findByEmail(userDto.getEmail());
        if (user != null) {
            return false;
        }
        User newUser = modelMapper.map(userDto, User.class);
        newUser.setMdp(passwordEncoder.encode(newUser.getMdp()));
        newUser.setActive(true);
        userRepo.save(newUser);
        return true;
    }

    public String authenticate(UserRequestDTO URDTO) throws AuthException {
        User user = userRepo.findByEmail(URDTO.getEmail());
        if (user == null) {
            throw new AuthException("Utilisateur non trouvé.");
        }

        if (!passwordEncoder.matches(URDTO.getMdp(), user.getMdp())) {
            throw new AuthException("Mot de passe incorrect.");
        }

        return jwtTokenProvider.generateToken(user.getEmail());
    }

    public List<UserResponseDTO> getAllUsers(){
        return userRepo.findAll().stream()
                .map(u -> modelMapper.map(u, UserResponseDTO.class))
                .toList();
    }

    public UserResponseDTO getUser(String email){
        User user = userRepo.findByEmail(email);
        if (user == null){return null;}
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public boolean updateUser(UserRequestDTO modifiedUserDto){
        User user = userRepo.findByEmail(modifiedUserDto.getEmail());
        if (user == null){return false;}
        modelMapper.map(modifiedUserDto, user);
        userRepo.save(user);
        return true;
    }

    public boolean deleteUser(String email){
        User user = userRepo.findByEmail(email);
        if (user == null){return false;}
        userRepo.delete(user);
        return true;
    }

    public List<SalonResponseDTO> getMySalon(String email){
        User user = userRepo.findByEmail(email);
        List<Salon> salons =  user.getSalonsCree();
        return salons.stream()
                .map(s -> modelMapper.map(s, SalonResponseDTO.class))
                .toList();
    }

    public List<SalonResponseDTO> getSalon(String email){
        User user = userRepo.findByEmail(email);
        List<Salon> salons =  user.getSalons();
        return salons.stream()
                .map(s -> modelMapper.map(s, SalonResponseDTO.class))
                .toList();
    }

    public boolean setActive(String email, boolean active){
        User user = userRepo.findByEmail(email);
        if (user == null) return false;
        user.setActive(active);
        userRepo.save(user);
        return true;
    }

    public boolean setActiveSalon(String email, String titre){
        User user = userRepo.findByEmail(email);
        Salon salon = salonRepo.findByTitre(titre);
        if (user == null ||
                salon == null ||
                user.getSalons().stream().noneMatch(s -> s.getTitre().equals(titre))){
            return false;
        }

        user.setSalonActif(salon);
        userRepo.save(user);
        return true;
    }

    public boolean clearActiveSalon(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) return false;
        user.setSalonActif(null);
        userRepo.save(user);
        return true;
    }

}