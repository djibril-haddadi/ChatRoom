package com.djibrilhaddadi.chatrooms.repository;

import com.djibrilhaddadi.chatrooms.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    Message findById(long id);
    List<Message> findBySalon_TitreOrderByDateAsc(String titre);
}
