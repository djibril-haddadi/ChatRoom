package com.djibrilhaddadi.chatrooms.repository;

import com.djibrilhaddadi.chatrooms.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation,Long> {
    public Invitation findById(long id);
}
