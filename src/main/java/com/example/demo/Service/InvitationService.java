package com.example.demo.Service;

import Repositories.InvitationRepository;
import Repositories.SalonRepository;
import com.example.demo.*;
import com.example.demo.DTO.InvitationRequestDTO;
import com.example.demo.DTO.InvitationResponseDTO;
import com.example.demo.DTO.SalonRequestDTO;
import com.example.demo.DTO.SalonResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Service
public class InvitationService {
    @Autowired
    private InvitationRepository InvitationRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SalonRepository salonRepo;

    public boolean addInvitation(InvitationRequestDTO invitationDTO){
        Salon salon = salonRepo.findByTitre(invitationDTO.getSalonTitre());
        if (salon == null) {
            return false;
        }

        Invitation invitation = modelMapper.map(invitationDTO, Invitation.class);
        invitation.setSalon(salon);
        invitation.setEtat(Etat.EN_ATTENTE);
        invitation.setDate(new Date());

        InvitationRepo.save(invitation);
        return true;
    }

    public List<InvitationResponseDTO> getInvitation(){
        return InvitationRepo.findAll().stream()
                .map(i -> modelMapper.map(i, InvitationResponseDTO.class))
                .toList();
    }

    public InvitationResponseDTO getInvitation(long id){
        return modelMapper.map(InvitationRepo.findById(id), InvitationResponseDTO.class);
    }


    public boolean modifyInvitation(InvitationRequestDTO invitationBody){
        Invitation invitation = InvitationRepo.findById(invitationBody.getId());
        if (invitation == null){return false;}
        modelMapper.map(invitationBody, invitation);
        InvitationRepo.save(invitation);
        return true;
    }

    public boolean deleteInvitation(long id){
        Invitation invitation = InvitationRepo.findById(id);
        if (invitation == null){return false;}
        InvitationRepo.delete(invitation);
        return true;
    }
}
