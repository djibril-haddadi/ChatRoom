package com.djibrilhaddadi.chatrooms.repository;

import com.djibrilhaddadi.chatrooms.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);
}
