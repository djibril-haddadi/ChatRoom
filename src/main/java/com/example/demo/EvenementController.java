package com.example.demo;

import Repositories.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
public class EvenementController {
    @Autowired
    private EvenementRepository evenementRepo;

    @GetMapping("/addEvenement")
    public String addEvenement() {
        return "addEvenement";
    }

    @PostMapping("/addEvenement")
    public ResponseEntity<String> addEvenement(@RequestParam("date") Date date){
        Evenement e1 = new Evenement(date);
        evenementRepo.save(e1);
        return ResponseEntity.ok("Evenement added succesfully");
    }

    @GetMapping("/getEvenements")
    public ResponseEntity<List<Evenement>> getEvenements(){
        List<Evenement> evenementList = evenementRepo.findAll();

        return ResponseEntity.ok(evenementList);
    }

    @GetMapping("/getEvenement")
    public ResponseEntity<Evenement> getEvenement(@RequestParam("id") long id){
        Evenement evenement = evenementRepo.findById(id);

        return ResponseEntity.ok(evenement);
    }

    @PutMapping("/modifyEvenement")
    public ResponseEntity<String> modifyEvenement(@RequestBody Evenement evenementBody){
        Evenement evenement = evenementRepo.findById(evenementBody.getId());
        if (evenement == null){return ResponseEntity.ok("Evenement does not exist, could not be modified");}
        evenementRepo.save(evenementBody);
        return ResponseEntity.ok("Evenement modified successfully");
    }

    @DeleteMapping("/deleteEvenement")
    public ResponseEntity<String> deleteEvenement(@RequestParam("id") long id){
        Evenement evenement = evenementRepo.findById(id);
        if (evenement == null){return ResponseEntity.ok("Evenement does not exist, could not be deleted");}
        evenementRepo.delete(evenement);
        return ResponseEntity.ok("evenement deleted successfully");
    }
}
