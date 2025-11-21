package Service;

import com.example.demo.*;
import Repositories.EvenementRepository;
import com.example.demo.Evenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Service
public class EvenementService {
    @Autowired
    private EvenementRepository evenementRepo;

    public String addEvenement() {
        return "addEvenement";
    }

    public String addEvenement(@RequestParam("date") Date date){
        Evenement e1 = new Evenement(date);
        evenementRepo.save(e1);
        return ("Evenement added succesfully");
    }

    public List<Evenement> getEvenements(){
        return evenementRepo.findAll();
    }

    public Evenement getEvenement(@RequestParam("id") long id){
        Evenement evenement = evenementRepo.findById(id);

        return evenement;
    }

    public String modifyEvenement(@RequestBody Evenement evenementBody){
        Evenement evenement = evenementRepo.findById(evenementBody.getId());
        if (evenement == null){return "Evenement does not exist, could not be modified";}
        evenementRepo.save(evenementBody);
        return "Evenement modified successfully";
    }

    public String deleteEvenement(@RequestParam("id") long id){
        Evenement evenement = evenementRepo.findById(id);
        if (evenement == null){return "Evenement does not exist, could not be deleted";}
        evenementRepo.delete(evenement);
        return "evenement deleted successfully";
    }}
