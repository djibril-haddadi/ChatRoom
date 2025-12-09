package com.example.demo.Controller;

import com.example.demo.Salon;
import com.example.demo.Service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalonController {
    @Autowired
    private SalonService salonService;

    @GetMapping("/Salon/add")
    public String addSalon() {
        return "addSalon";
    }

    @PostMapping("/Salon/add")
    public ResponseEntity<String> addSalon(@RequestParam("titre") String titre){
        return ResponseEntity.ok(salonService.addSalon(titre));
    }

    @GetMapping("/Salon/getByTitre")
    public ResponseEntity<Salon> getSalon(@RequestParam("titre") String titre){
        return ResponseEntity.ok(salonService.getSalon(titre));
    }

    @GetMapping("/Salon/get")
    public ResponseEntity<List<Salon>> getSalons(){
        return ResponseEntity.ok(salonService.getSalon());
    }

    @PutMapping("/Salon/modify")
    public ResponseEntity<String> modifySalon(@RequestBody Salon salonBody){
        return ResponseEntity.ok(salonService.modifySalon(salonBody));
    }

    @DeleteMapping("/Salon/delete")
    public ResponseEntity<String> deleteSalon(@RequestParam("titre") String titre){
        return ResponseEntity.ok(salonService.deleteSalon(titre));
    }
}
