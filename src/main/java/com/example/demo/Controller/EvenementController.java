package com.example.demo.Controller;

import com.example.demo.Evenement;
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

    @GetMapping("/Evenement/add")
    public String addEvenement() {
        return evenementService.addEvenement();
    }

    @PostMapping("/Evenement/add")
    public ResponseEntity<String> addEvenement(@RequestParam("date") Date date){
        return ResponseEntity.ok(evenementService.addEvenement(date));
    }

    @GetMapping("/Evenements/get")
    public ResponseEntity<List<Evenement>> getEvenements(){
        return ResponseEntity.ok(evenementService.getEvenements());
    }

    @GetMapping("/Evenement/getById")
    public ResponseEntity<Evenement> getEvenement(@RequestParam("id") long id){
        return ResponseEntity.ok(evenementService.getEvenement(id));
    }

    @PutMapping("/Evenement/modify")
    public ResponseEntity<String> modifyEvenement(@RequestBody Evenement evenementBody){
        return ResponseEntity.ok(evenementService.modifyEvenement(evenementBody));
    }

    @DeleteMapping("/Evenement/delete")
    public ResponseEntity<String> deleteEvenement(@RequestParam("id") long id){
        return ResponseEntity.ok(evenementService.deleteEvenement(id));
    }
}
