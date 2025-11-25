package com.example.demo.Controller;

import com.example.demo.Salon;
import com.example.demo.Service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SalonController {
    @Autowired
    private SalonService salonService;

    @GetMapping("/addSalon")
    public String addSalon() {
        return "addSalon";
    }

    @PostMapping("/addSalon")
    public ResponseEntity<String> addSalon(@RequestParam("titre") String titre){
        return ResponseEntity.ok(salonService.addSalon(titre));
    }

    @GetMapping("/getSalon")
    public ResponseEntity<Salon> getSalon(@RequestParam("titre") String titre){
        return ResponseEntity.ok(salonService.getSalon(titre));
    }

    @PutMapping("/modifySalon")
    public ResponseEntity<String> modifySalon(@RequestBody Salon salonBody){
        return ResponseEntity.ok(salonService.modifySalon(salonBody));
    }

    @DeleteMapping("/deleteSalon")
    public ResponseEntity<String> deleteSalon(@RequestParam("titre") String titre){
        return ResponseEntity.ok(salonService.deleteSalon(titre));
    }
}
