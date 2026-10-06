package com.djibrilhaddadi.chatrooms.controller;

import com.djibrilhaddadi.chatrooms.dto.SuppressionRequestDTO;
import com.djibrilhaddadi.chatrooms.dto.SuppressionResponseDTO;
import com.djibrilhaddadi.chatrooms.service.SuppressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SuppressionController {

    @Autowired
    private SuppressionService suppressionServ;

    @PostMapping("/Suppression/add")
    public ResponseEntity<String> addSuppression(@RequestBody SuppressionRequestDTO suppressionDTO) {
        if (suppressionServ.addSuppression(suppressionDTO)) {
            return ResponseEntity.ok("Suppression created successfully");
        }
        return ResponseEntity.status(404).body("Salon or user not found, could not create suppression");
    }

    @GetMapping("/Suppression/get")
    public ResponseEntity<List<SuppressionResponseDTO>> getSuppression() {
        return ResponseEntity.ok(suppressionServ.getSuppression());
    }

    @GetMapping("/Suppression/getById")
    public ResponseEntity<SuppressionResponseDTO> getSuppression(@RequestParam("id") long id) {
        SuppressionResponseDTO suppression = suppressionServ.getSuppression(id);
        if (suppression == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(suppression);
    }

    @PutMapping("/Suppression/modify")
    public ResponseEntity<String> modifySuppression(@RequestBody SuppressionRequestDTO suppressionBody) {
        if (suppressionServ.modifySuppression(suppressionBody)) {
            return ResponseEntity.ok("Suppression modified successfully");
        }
        return ResponseEntity.status(404).body("Suppression could not be found");
    }

    @DeleteMapping("/Suppression/delete")
    public ResponseEntity<String> deleteSuppression(@RequestParam("id") long id) {
        if (suppressionServ.deleteSuppression(id)) {
            return ResponseEntity.ok("Suppression deleted successfully");
        }
        return ResponseEntity.status(404).body("Suppression could not be found");
    }
}
