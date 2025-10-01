package com.example.demo;

import Repositories.SuppressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
public class SuppressionController {
    @Autowired
    private SuppressionRepository SuppressionRepo;

    @GetMapping("/addSuppression")
    public String addSuppression() {
        return "addSuppression";
    }

    @PostMapping("/addSuppression")
    public ResponseEntity<String> addSuppression(@RequestParam("userSupprime") User userSupprime, @RequestParam("raison") String raison, @RequestParam("date") Date date){
        Suppression s1 = new Suppression(userSupprime, raison, date);
        SuppressionRepo.save(s1);
        return ResponseEntity.ok("Suppression added succesfully");
    }
}
