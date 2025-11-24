package com.example.demo.Service;

import Repositories.UserRepository;
import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    private final UserRepository userRepo;

    public UserService(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    public void createUser(User user){
        userRepo.save(user);
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUser(String email){
        return userRepo.findByEmail(email);
    }

    public boolean updateUser(User modifiedUser){
        User user = userRepo.findByEmail(modifiedUser.getEmail());
        if (user == null){return false;}
        userRepo.save(modifiedUser);
        return true;
    }
    public boolean deleteUser(String email){
        User user = userRepo.findByEmail(email);
        if (user == null){return false;}
        userRepo.delete(user);
        return true;
    }
}