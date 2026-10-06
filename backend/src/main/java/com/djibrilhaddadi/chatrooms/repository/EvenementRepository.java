package com.djibrilhaddadi.chatrooms.repository;

import com.djibrilhaddadi.chatrooms.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement,Long> {
    Evenement findById(long id);
}
