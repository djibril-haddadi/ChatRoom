package com.example.demo.Service;

import Repositories.UserRepository;
import com.example.demo.DTO.UserRequestDTO;
import com.example.demo.DTO.UserResponseDTO;
import com.example.demo.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    private final UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    public void createUser(UserRequestDTO userDto){
        User user = modelMapper.map(userDto, User.class);
        userRepo.save(user);
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
}