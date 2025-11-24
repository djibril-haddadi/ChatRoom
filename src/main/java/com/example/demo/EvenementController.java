package com.example.demo;

import com.example.demo.Service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
public class EvenementController {
    @Autowired
    private EvenementService evenementService;

    @GetMapping("/addEvenement")
    public String addEvenement() {
        return evenementService.addEvenement();
    }

    @PostMapping("/addEvenement")
    public ResponseEntity<String> addEvenement(@RequestParam("date") Date date){
        return ResponseEntity.ok(evenementService.addEvenement(date));
    }

    @GetMapping("/getEvenements")
    public ResponseEntity<List<Evenement>> getEvenements(){
        return ResponseEntity.ok(evenementService.getEvenements());
    }

    @GetMapping("/getEvenement")
    public ResponseEntity<Evenement> getEvenement(@RequestParam("id") long id){
        return ResponseEntity.ok(evenementService.getEvenement(id));
    }

    @PutMapping("/modifyEvenement")
    public ResponseEntity<String> modifyEvenement(@RequestBody Evenement evenementBody){
        return ResponseEntity.ok(evenementService.modifyEvenement(evenementBody));
    }

    @DeleteMapping("/deleteEvenement")
    public ResponseEntity<String> deleteEvenement(@RequestParam("id") long id){
       return ResponseEntity.ok(evenementService.deleteEvenement(id));
    }
}
