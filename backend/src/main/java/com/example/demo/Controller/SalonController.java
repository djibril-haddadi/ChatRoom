package com.example.demo.Controller;

import com.example.demo.DTO.MessageResponseDTO;
import com.example.demo.DTO.SalonRequestDTO;
import com.example.demo.DTO.SalonResponseDTO;
import com.example.demo.Service.SalonService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalonController {
    @Autowired
    private SalonService salonService;

    @Autowired
    private UserService userServ;

    @PostMapping("/Salon/add")
    public ResponseEntity<String> addSalon(@RequestBody() SalonRequestDTO SalonDTO){
        salonService.addSalon(SalonDTO);
        return ResponseEntity.ok("Salon created successfully");
    }

    @GetMapping("/Salon/getByTitre")
    public ResponseEntity<SalonResponseDTO> getSalon(@RequestParam("titre") String titre){
        return ResponseEntity.ok(salonService.getSalon(titre));
    }

    @GetMapping("/Salon/get")
    public ResponseEntity<List<SalonResponseDTO>> getSalons(){
        return ResponseEntity.ok(salonService.getSalon());
    }

    @PutMapping("/Salon/modify")
    public ResponseEntity<String> modifySalon(@RequestBody SalonRequestDTO salonBody){
        if (salonService.modifySalon(salonBody)){
            return ResponseEntity.ok("Salon modified sucessfully");
        }
        return ResponseEntity.status(404).body("Salon could not be found");
    }

    @DeleteMapping("/Salon/delete")
    public ResponseEntity<String> deleteSalon(@RequestParam("titre") String titre){
        if (salonService.deleteSalon(titre)){
            return ResponseEntity.ok("Salon deleted sucessfully");
        }
        return ResponseEntity.status(404).body("Salon could not be found");
    }

    @GetMapping("/Salon/{email}/creator")
    public ResponseEntity<List<SalonResponseDTO>> getSalonByCreator(@PathVariable String email){
        return ResponseEntity.ok(userServ.getMySalon(email));
    }

    @GetMapping("/Salon/{email}/member")
    public ResponseEntity<List<SalonResponseDTO>> getSalonByMember(@PathVariable String email){
        return ResponseEntity.ok(userServ.getSalon(email));
    }

}