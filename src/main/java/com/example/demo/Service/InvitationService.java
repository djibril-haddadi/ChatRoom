package com.example.demo.Service;

import Repositories.InvitationRepository;
import com.example.demo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Service
public class InvitationService {
    @Autowired
    private InvitationRepository InvitationRepo;

    public String addInvitation() {
        return "addInvitation";
    }

    public String addInvitation(Etat etat, User userInvite, Date date){
        Invitation u1 = new Invitation(etat, userInvite, date);
        InvitationRepo.save(u1);
        return "Invitation created successfully.";
    }

    public List<Invitation> getInvitation(){
        List<Invitation> invitationList = InvitationRepo.findAll();

        return invitationList;
    }

    public Invitation getInvitation(long id){
        Invitation invitation = InvitationRepo.findById(id);

        return invitation;
    }

    public String modifyInvitation(Invitation invitationBody){
        Invitation invitation = InvitationRepo.findById(invitationBody.getId());
        if (invitation == null){return "Invitation does not exist, could not be modified";}
        InvitationRepo.save(invitationBody);
        return "Invitation modified successfully";
    }

    public String deleteInvitation(long id){
        Invitation invitation = InvitationRepo.findById(id);
        if (invitation == null){return "Invitation does not exist, could not be deleted";}
        InvitationRepo.delete(invitation);
        return "invitation deleted successfully";
    }
}
