package com.example.demo.Service;

import Repositories.SalonRepository;
import com.example.demo.Salon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class SalonService {
    @Autowired
    private SalonRepository salonRepo;

    @GetMapping("/addSalon")
    public String addSalon() {
        return "addSalon";
    }

    public String addSalon(String titre){
        Salon s1 = new Salon(titre);
        salonRepo.save(s1);
        return ("Salon added succesfully");
    }

    public List<Salon> getSalon(){
        List<Salon> salonList = salonRepo.findAll();

        return (salonList);
    }

    public Salon getSalon(String titre){
        Salon salon = salonRepo.findByTitre(titre);

        return (salon);
    }

    public String modifySalon(Salon salonBody){
        Salon salon = salonRepo.findByTitre(salonBody.getTitre());
        if (salon == null){return ("Salon does not exist, could not be modified");}
        salonRepo.save(salonBody);
        return ("Salon modified successfully");
    }

    public String deleteSalon(String titre){
        Salon salon = salonRepo.findByTitre(titre);
        if (salon == null){return ("Salon does not exist, could not be deleted");}
        salonRepo.delete(salon);
        return ("salon deleted successfully");
    }
}
