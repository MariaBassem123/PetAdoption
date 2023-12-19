package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.Adoption;
import com.example.PetAdoptionSystem.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adoptions")
public class AdoptionController {
    @Autowired
    private AdoptionService adoptionService;

    @PostMapping("/save")
    public ResponseEntity<String> saveAdoption(@RequestBody Adoption adoption) {
        try {
            adoptionService.save(adoption);
            return ResponseEntity.status(HttpStatus.CREATED).body("Adoption saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving adoption");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Adoption>> getAllAdoptions() {
        List<Adoption> adoptions = adoptionService.getAllAdoptions();
        return ResponseEntity.ok(adoptions);
    }
}
