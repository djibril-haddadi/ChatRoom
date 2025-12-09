package com.example.demo.Controller;

import com.example.demo.Service.SuppressionService;
import com.example.demo.Suppression;
import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
public class SuppressionController {
    @Autowired
    private SuppressionService suppressionServ;

    @GetMapping("/Suppression/add")
    public String addSuppression() {
        return "addSuppression";
    }

    @PostMapping("/Suppression/add")
    public ResponseEntity<String> addSuppression(
            @RequestParam("id") long id,
            @RequestParam("userSupprime") User newUserSupprime,
            @RequestParam("raison") String newRaison,
            @RequestParam("date") Date newDate) {
        return ResponseEntity.ok(suppressionServ.addSuppression(id, newUserSupprime, newRaison, newDate));
    }

    @GetMapping("/Suppression/get")
    public ResponseEntity<List<Suppression>> getSuppression(){
        return ResponseEntity.ok(suppressionServ.getSuppression());
    }

    @GetMapping("/Suppression/getById")
    public ResponseEntity<Suppression> getSuppression(@RequestParam("id") long id){
        return ResponseEntity.ok(suppressionServ.getSuppression(id));
    }

    @PutMapping("/Suppression/modify")
    public ResponseEntity<String> modifySuppression(@RequestBody Suppression suppressionBody){
        return ResponseEntity.ok(suppressionServ.modifySuppression(suppressionBody));
    }

    @DeleteMapping("/Suppression/delete")
    public ResponseEntity<String> deleteSuppression(@RequestParam("id") long id){
        return ResponseEntity.ok(suppressionServ.deleteSuppression(id));
    }
}
