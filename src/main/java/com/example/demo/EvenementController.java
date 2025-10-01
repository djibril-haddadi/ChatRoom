package com.example.demo;

import Repositories.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
public class EvenementController {
    @Autowired
    private EvenementRepository EvenementRepo;

    @GetMapping("/addEvenement")
    public String addEvenement() {
        return "addEvenement";
    }

    @PostMapping("/addEvenement")
    public ResponseEntity<String> addEvenement(@RequestParam("date") Date date){
        Evenement e1 = new Evenement(date);
        EvenementRepo.save(e1);
        return ResponseEntity.ok("Evenement added succesfully");
    }
}
