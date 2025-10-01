package com.example.demo;

import Repositories.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
