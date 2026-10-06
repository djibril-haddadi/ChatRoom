package com.djibrilhaddadi.chatrooms.service;

import com.djibrilhaddadi.chatrooms.dto.InvitationRequestDTO;
import com.djibrilhaddadi.chatrooms.dto.InvitationResponseDTO;
import com.djibrilhaddadi.chatrooms.entity.Etat;
import com.djibrilhaddadi.chatrooms.entity.Invitation;
import com.djibrilhaddadi.chatrooms.entity.Salon;
import com.djibrilhaddadi.chatrooms.entity.User;
import com.djibrilhaddadi.chatrooms.repository.InvitationRepository;
import com.djibrilhaddadi.chatrooms.repository.SalonRepository;
import com.djibrilhaddadi.chatrooms.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InvitationService {
    @Autowired
    private InvitationRepository invitationRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SalonRepository salonRepo;
    @Autowired
    private UserRepository userRepo;

    public boolean addInvitation(InvitationRequestDTO invitationDTO) {
        Salon salon = salonRepo.findByTitre(invitationDTO.getSalonTitre());
        if (salon == null) {
            return false;
        }

        User invited = userRepo.findByEmail(invitationDTO.getInvitedEmail());
        if (invited == null) {
            return false;
        }

        Invitation invitation = new Invitation();
        invitation.setSalon(salon);
        invitation.setInvited(invited);
        invitation.setUserInvite(invited);
        invitation.setEtat(Etat.EN_ATTENTE);
        invitation.setDate(new Date());

        invitationRepo.save(invitation);
        return true;
    }

    public List<InvitationResponseDTO> getInvitation() {
        return invitationRepo.findAll().stream()
                .map(i -> modelMapper.map(i, InvitationResponseDTO.class))
                .toList();
    }

    public InvitationResponseDTO getInvitation(long id) {
        return modelMapper.map(invitationRepo.findById(id), InvitationResponseDTO.class);
    }

    public boolean modifyInvitation(InvitationRequestDTO invitationBody) {
        Invitation invitation = invitationRepo.findById(invitationBody.getId());
        if (invitation == null) {
            return false;
        }
        modelMapper.map(invitationBody, invitation);
        invitationRepo.save(invitation);
        return true;
    }

    public boolean deleteInvitation(long id) {
        Invitation invitation = invitationRepo.findById(id);
        if (invitation == null) {
            return false;
        }
        invitationRepo.delete(invitation);
        return true;
    }
}
