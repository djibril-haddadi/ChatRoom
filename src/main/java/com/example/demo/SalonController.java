package com.example.demo;

import Repositories.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalonController {
    @Autowired
    private SalonRepository salonRepo;

    @GetMapping("/addSalon")
    public String addSalon() {
        return "addSalon";
    }

    @PostMapping("/addSalon")
    public ResponseEntity<String> addSalon(@RequestParam("titre") String titre){
        Salon s1 = new Salon(titre);
        salonRepo.save(s1);
        return ResponseEntity.ok("Salon added succesfully");
    }

    @GetMapping("/getSalons")
    public ResponseEntity<List<Salon>> getSalon(){
        List<Salon> salonList = salonRepo.findAll();

        return ResponseEntity.ok(salonList);
    }

    @GetMapping("/getSalon")
    public ResponseEntity<Salon> getSalon(@RequestParam("titre") String titre){
        Salon salon = salonRepo.findByTitre(titre);

        return ResponseEntity.ok(salon);
    }

    @PutMapping("/modifySalon")
    public ResponseEntity<String> modifySalon(@RequestBody Salon salonBody){
        Salon salon = salonRepo.findByTitre(salonBody.getTitre());
        if (salon == null){return ResponseEntity.ok("Salon does not exist, could not be modified");}
        salonRepo.save(salonBody);
        return ResponseEntity.ok("Salon modified successfully");
    }

    @DeleteMapping("/deleteSalon")
    public ResponseEntity<String> deleteSalon(@RequestParam("titre") String titre){
        Salon salon = salonRepo.findByTitre(titre);
        if (salon == null){return ResponseEntity.ok("Salon does not exist, could not be deleted");}
        salonRepo.delete(salon);
        return ResponseEntity.ok("salon deleted successfully");
    }
}
