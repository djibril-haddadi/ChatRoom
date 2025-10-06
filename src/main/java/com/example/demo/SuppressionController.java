package com.example.demo;

import Repositories.SuppressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
public class SuppressionController {
    @Autowired
    private SuppressionRepository suppressionRepo;

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
        Suppression u1 = new Suppression(id, newUserSupprime, newRaison, newDate);
        suppressionRepo.save(u1);
        return ResponseEntity.ok("Suppression created successfully.");
    }

    @GetMapping("/getSuppressions")
    public ResponseEntity<List<Suppression>> getSuppression(){
        List<Suppression> suppressionList = suppressionRepo.findAll();

        return ResponseEntity.ok(suppressionList);
    }

    @GetMapping("/getSuppression")
    public ResponseEntity<Suppression> getSuppression(@RequestParam("id") long id){
        Suppression suppression = suppressionRepo.findById(id);

        return ResponseEntity.ok(suppression);
    }

    @PutMapping("/modifySuppression")
    public ResponseEntity<String> modifySuppression(@RequestBody Suppression suppressionBody){
        Suppression suppression = suppressionRepo.findById(suppressionBody.getId());
        if (suppression == null){return ResponseEntity.ok("Suppression does not exist, could not be modified");}
        suppressionRepo.save(suppressionBody);
        return ResponseEntity.ok("Suppression modified successfully");
    }

    @DeleteMapping("/deleteSuppression")
    public ResponseEntity<String> deleteSuppression(@RequestParam("id") long id){
        Suppression suppression = suppressionRepo.findById(id);
        if (suppression == null){return ResponseEntity.ok("Suppression does not exist, could not be deleted");}
        suppressionRepo.delete(suppression);
        return ResponseEntity.ok("suppression deleted successfully");
    }
}
