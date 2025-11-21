package com.example.demo;

import Service.SuppressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
public class SuppressionController {
    @Autowired
    private SuppressionService suppressionServ;

    @GetMapping("/addSuppression")
    public String addSuppression() {
        return "addSuppression";
    }

    @PostMapping("/addSuppression")
    public ResponseEntity<String> addSuppression(
            @RequestParam("id") long id,
            @RequestParam("userSupprime") User newUserSupprime,
            @RequestParam("raison") String newRaison,
            @RequestParam("date") Date newDate) {
        return ResponseEntity.ok(suppressionServ.addSuppression(id, newUserSupprime, newRaison, newDate));
    }

    @GetMapping("/getSuppressions")
    public ResponseEntity<List<Suppression>> getSuppression(){
        return ResponseEntity.ok(suppressionServ.getSuppression());
    }

    @GetMapping("/getSuppression")
    public ResponseEntity<Suppression> getSuppression(@RequestParam("id") long id){
        return ResponseEntity.ok(suppressionServ.getSuppression(id));
    }

    @PutMapping("/modifySuppression")
    public ResponseEntity<String> modifySuppression(@RequestBody Suppression suppressionBody){
        return ResponseEntity.ok(suppressionServ.modifySuppression(suppressionBody));
    }

    @DeleteMapping("/deleteSuppression")
    public ResponseEntity<String> deleteSuppression(@RequestParam("id") long id){
        return ResponseEntity.ok(suppressionServ.deleteSuppression(id));
    }
}
