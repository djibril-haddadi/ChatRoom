package com.djibrilhaddadi.chatrooms.service;

import com.djibrilhaddadi.chatrooms.repository.SalonRepository;
import com.djibrilhaddadi.chatrooms.repository.UserRepository;
import com.djibrilhaddadi.chatrooms.dto.SalonResponseDTO;
import com.djibrilhaddadi.chatrooms.dto.UserRequestDTO;
import com.djibrilhaddadi.chatrooms.dto.UserResponseDTO;
import com.djibrilhaddadi.chatrooms.entity.Salon;
import com.djibrilhaddadi.chatrooms.security.JwtTokenProvider;
import com.djibrilhaddadi.chatrooms.entity.User;
import jakarta.persistence.EntityNotFoundException;
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

    /*public List<UserResponseDTO> getAllUsers(){
        return userRepo.findAll().stream()
                .map(u -> modelMapper.map(u, UserResponseDTO.class))
                .toList();
    }*/
    public List<UserResponseDTO> getAllUsers(){
        return userRepo.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    /*public UserResponseDTO getUser(String email){
        User user = userRepo.findByEmail(email);
        if (user == null){return null;}
        return modelMapper.map(user, UserResponseDTO.class);
    }*/
    //------------------

    public UserResponseDTO getUser(String email) {
        try {
            User user = userRepo.findById(email)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé: " + email));

            return toDto(user);
        } catch (Exception e) {
            System.err.println("Erreur dans UserService.getUser: " + e.getMessage());
            throw e; // Relance l'exception pour que le contrôleur la capture
        }
    }

    private UserResponseDTO toDto(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setEmail(user.getEmail());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setPseudo(user.getPseudo());
        dto.setActive(user.isActive());

        // Gestion sécurisée de salonActif
        if (user.getSalonActif() != null) {
            dto.setSalonActif(user.getSalonActif().getTitre());
        }

        return dto;
    }



    //-----------------
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

    /*public boolean setActiveSalon(String email, String titre){
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
    }*/
    public boolean setActiveSalon(String email, String titre) {
        try {
            // 1. Vérifier que l'utilisateur existe
            User user = userRepo.findById(email)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            // 2. Vérifier que le salon existe
            Salon salon = salonRepo.findByTitre(titre);
            if (salon == null) {
                throw new RuntimeException("Salon non trouvé");
            }

            // 3. Mettre à jour le salon actif de l'utilisateur
            user.setSalonActif(salon);
            userRepo.save(user);

            return true;
        } catch (Exception e) {
            System.err.println("Erreur dans setActiveSalon: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean clearActiveSalon(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) return false;
        user.setSalonActif(null);
        userRepo.save(user);
        return true;
    }

    public boolean acceptInvitation(String email, String salonTitre) {
        try {
            // 1. Trouver l'utilisateur et le salon
            User user = userRepo.findById(email)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            Salon salon = salonRepo.findByTitre(salonTitre);
                    //.orElseThrow(() -> new RuntimeException("Salon non trouvé"));

            // 2. Ajouter l'utilisateur au salon (relation bidirectionnelle)
            if (!user.getSalons().contains(salon)) {
                user.getSalons().add(salon);
            }
            if (!salon.getUserList().contains(user)) {
                salon.getUserList().add(user);
            }

            // 3. Mettre à jour le salon actif
            user.setSalonActif(salon);

            // 4. Sauvegarder les modifications
            userRepo.save(user);
            salonRepo.save(salon);

            return true;
        } catch (Exception e) {
            System.err.println("Erreur dans acceptInvitation: " + e.getMessage());
            return false;
        }
    }
    public List<UserResponseDTO> getSalonMembersConnected(String titre){
        Salon salon = salonRepo.findByTitre(titre);
        if (salon == null){
            throw new EntityNotFoundException("Salon not found with titre: " + titre);
        }
        return salon.getUserConnected().stream()
                .map(this::toDto)
                .toList();
    }
}